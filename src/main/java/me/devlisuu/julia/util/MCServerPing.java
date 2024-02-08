package me.devlisuu.julia.util;

import com.google.gson.Gson;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

import static me.devlisuu.julia.util.VarInt.readVarInt;
import static me.devlisuu.julia.util.VarInt.writeVarInt;

/**
 * @author zh32 <zh32 at zh32.de>
 */
public class MCServerPing {
    final static byte HANDSHAKE_ID = 0x00;
    final static byte PING_ID = 0x01;

    private final Gson gson = new Gson();

    private final InetSocketAddress address;
    private int protocolVer = 760;
    private int timeout = 7000;

    public MCServerResponse fetchData() throws IOException {
        Socket socket = new Socket();
        socket.setSoTimeout(this.timeout);
        socket.connect(address, timeout);

        InputStream inputStream = socket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream handshake = new DataOutputStream(byteArrayOutputStream);

        handshake.writeByte(HANDSHAKE_ID);
        writeVarInt(handshake, protocolVer);
        writeVarInt(handshake, this.address.getHostString().length()); // host length
        handshake.writeBytes(this.address.getHostString()); // host string
        handshake.writeShort(this.address.getPort()); // port
        writeVarInt(handshake, 1); // state (1 for handshake)

        writeVarInt(dataOutputStream, byteArrayOutputStream.size()); //prepend size
        dataOutputStream.write(byteArrayOutputStream.toByteArray()); //write handshake packet

        dataOutputStream.writeByte(0x01); // size is only 1
        dataOutputStream.writeByte(HANDSHAKE_ID);
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        int packet_size = readVarInt(dataInputStream);
        int packet_id = readVarInt(dataInputStream);
        int packet_length = readVarInt(dataInputStream); //length of json string

        if (packet_id == -1) {
            throw new IOException("Premature end of stream.");
        }
        if (packet_length == -1) {
            throw new IOException("Premature end of stream.");
        }
        if (packet_id != 0x00) { // response packet id
            throw new IOException("Invalid packetID");
        }
        if (packet_length == 0) {
            throw new IOException("Invalid string length.");
        }

        byte[] in = new byte[packet_length];
        dataInputStream.readFully(in); //read json string
        String json = new String(in);

        long now = System.currentTimeMillis();
        dataOutputStream.writeByte(0x09); //size of packet
        dataOutputStream.writeByte(PING_ID);
        dataOutputStream.writeLong(now); // time!?

        readVarInt(dataInputStream);
        packet_id = readVarInt(dataInputStream);
        if (packet_id == -1) {
            throw new IOException("Premature end of stream.");
        }
        if (packet_id != 0x01) {
            throw new IOException("Invalid packetID");
        }

        long pingtime = dataInputStream.readLong(); // read response

        MCServerResponse response = gson.fromJson(json, MCServerResponse.class);
        response.setTime((int) (now - pingtime));

        dataOutputStream.close();
        outputStream.close();
        inputStreamReader.close();
        inputStream.close();
        socket.close();

        return response;
    }

    public MCServerPing(InetSocketAddress address) {
        this.address = address;
    }

    public MCServerPing(String address) {
        this.address = new InetSocketAddress(address, 25565);
    }

    public MCServerPing(String address, int port) {
        this.address = new InetSocketAddress(address, port);
    }

    public InetSocketAddress getAddress() {
        return address;
    }

    public void setProtocolVersion(int protocolVer) {
        this.protocolVer = protocolVer;
    }

    public int getProtocolVer() {
        return protocolVer;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int setTimeout() {
        return timeout;
    }
}

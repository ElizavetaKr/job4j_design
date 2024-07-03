package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {

                    String str = input.readLine();
                    if (str.contains("/?msg=Exit")) {
                        server.close();
                    } else if (str.contains("/?msg=Hello")) {
                        output.write(("HTTP/1.1 200 OK\r\n\r\n").getBytes());
                        output.write("Hello!".getBytes());
                    } else {
                        output.write(("HTTP/1.1 200 OK\r\n\r\n").getBytes());
                        String[] array = str.split(" ");
                        output.write(array[1].substring(6).getBytes());
                    }

                    for (String string = input.readLine(); string != null && !string.isEmpty(); string = input.readLine()) {
                        System.out.println(string);
                    }
                    output.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("Exception in log", e);
        }
    }
}
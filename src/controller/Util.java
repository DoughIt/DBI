package controller;

import info.monitorenter.cpdetector.io.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class Util {
    private final static CodepageDetectorProxy proxy;

    static {
        proxy = CodepageDetectorProxy.getInstance();
        //添加探测器，检测文本编码
        proxy.add(JChardetFacade.getInstance());
        proxy.add(ASCIIDetector.getInstance());
        proxy.add(UnicodeDetector.getInstance());
        proxy.add(new ByteOrderMarkDetector());
    }

    public static String getFileEncode(File file) {

        Charset charset = null;
        try {
            charset = proxy.detectCodepage(file.toURI().toURL());
            if (charset != null)
                return charset.name();
        } catch (IOException ignored) {
        }
        return null;
    }
}

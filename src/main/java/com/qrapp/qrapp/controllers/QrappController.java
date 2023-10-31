package com.qrapp.qrapp.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.awt.image.BufferedImage;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.*;
import com.google.zxing.qrcode.QRCodeWriter;



@RestController
public class QrappController {

    @GetMapping("/qr")
    public ResponseEntity<byte[]> generateQRCode() throws WriterException, IOException
    {
        QRCodeWriter qrCodeWriter  = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode("https://ma.linkedin.com/in/mouad-elbouchraoui", BarcodeFormat.QR_CODE, 200, 200);
        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();

        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(byteArray);
    }
    
}

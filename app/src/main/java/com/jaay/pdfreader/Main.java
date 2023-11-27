/*
 * Copyright 2023 Justice Ezenwoke Chukwuemeka
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jaay.pdfreader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.shockwave.pdfium.PdfDocument;

import java.io.File;

public class Main extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 100;
    private static final int PDF_SELECTION_CODE = 200;

    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        pdfView = findViewById(R.id.pdfView);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkPermission()) {
                selectPDFFile();
            } else {
                requestPermission();
            }
        } else {
            selectPDFFile();
        }
    }

    private boolean checkPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

    @Override

    public void onRequestPermissionsResult(int request_code, @NonNull String[] permissions, @NonNull int[] grant_results) {
        super.onRequestPermissionsResult(request_code, permissions, grant_results);
        if (request_code == PERMISSION_REQUEST_CODE && grant_results.length > 0 && grant_results[0] == PackageManager.PERMISSION_GRANTED) {
            selectPDFFile();
        } else {
            Toast.makeText(this, "Permission Denied! Please allow the permission to read PDF files.", Toast.LENGTH_SHORT).show();
        }
    }

    private void selectPDFFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        startActivityForResult(Intent.createChooser(intent, "Select PDF File"), PDF_SELECTION_CODE);
    }

    @Override
    protected void onActivityResult(int request_code, int result_code, @Nullable Intent data) {
        super.onActivityResult(request_code, result_code, data);
        if (request_code == PDF_SELECTION_CODE && result_code == RESULT_OK && data != null) {
            Uri selectedFileUri = data.getData();
            displayPDF(selectedFileUri);
        }
    }

    private void displayPDF(Uri fileUri) {
        pdfView.fromUri(fileUri)
                .defaultPage(0)
                .enableSwipe(true)
                .enableDoubletap(true)
                .swipeHorizontal(false)
                .onLoad(nbPages -> {
                })
                .onPageChange((page, pageCount) -> {
                })
                .scrollHandle(new DefaultScrollHandle(this))
                .enableAnnotationRendering(true)
                .password(null)
                .pageFitPolicy(FitPolicy.WIDTH)
                .load();
    }
}
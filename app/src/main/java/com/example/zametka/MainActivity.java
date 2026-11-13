package com.example.zametka;

import android.os.Bundle;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import java.io.File;
import java.io.FileOutputStream;
import android.widget.EditText;import android.widget.TextView;import android.widget.Toast;
import java.io.FileInputStream;
import java.io.IOException;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;import java.io.File;import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private final static  String FILE_NAME = "document.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private  File getExternalPath() {
        return new File(getExternalFilesDir(null), FILE_NAME);
    }
    public void saveText(View view) {

        try (FileOutputStream fos = new FileOutputStream(getExternalPath())) {
            EditText textBox = findViewById(R.id.editor);
            String text = textBox.getText().toString();
            fos.write(text.getBytes());
            Toast.makeText(this, "Файл сохранён", Toast.LENGTH_SHORT);
        } catch (IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void openText(View view) {
        TextView textView = findViewById(R.id.text);
        File file = getExternalPath();
        if(!file.exists()) return;
        try(FileInputStream fim = new FileInputStream(file)) {
            byte[] bytes = new byte[fim.available()];
            fim.read(bytes);
            String text = new String(bytes);
            textView.setText(text);
        }
        catch (IOException ex) {
            Toast.makeText(this,ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
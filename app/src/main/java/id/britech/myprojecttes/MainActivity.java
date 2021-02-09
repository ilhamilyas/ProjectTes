package id.britech.myprojecttes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText userName, password;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (userName.getText().toString().equals("ilham") && password.getText().toString().equals("12345")){
                    Intent intent = new Intent(MainActivity.this, LandingPageActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this,"gagal login", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
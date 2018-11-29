package ithspetts.ooad_lab1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private TextView textInfo;

    private Originator<String> originator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        originator = new Originator<>();

        editText = findViewById(R.id.editText);
        textInfo = findViewById(R.id.text_info);
    }

    public void sendClick(View view){
        String youInput = editText.getText().toString();

        originator.addUndoStack(youInput, value -> textInfo.setText(value));

        textInfo.setText(youInput);
        editText.setText("");
    }

    public void undoClick(View view) {
        originator.UndoInStack();
    }

    public void redoClick(View view) {
        originator.RedoInStack();
    }
}

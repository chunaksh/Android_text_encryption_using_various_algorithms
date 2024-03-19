package Hash;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.Algorithms.R;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;


public class HashMain extends Fragment {
    private Button Switch;
    private Button Hash_Buuton;
    private TextView Answer;
    private EditText Textfield_Text;
    private EditText Textfield_salt;
    private String message;
    private String salt;
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hash_main, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Switch = view.findViewById(R.id.Swtich);
        Hash_Buuton = view.findViewById(R.id.hash_Buuton);
        Answer = view.findViewById(R.id.Answer);
        Textfield_Text = view.findViewById(R.id.TextArea);
        Textfield_salt = view.findViewById(R.id.salt);

        return view;
    }



    public void hash(View view) throws Exception {
        if (Textfield_Text.length() == 0) {
            Toast.makeText(view.getContext(), "Enter a message to Hash", Toast.LENGTH_SHORT).show();
            return;
        }
        message = String.valueOf(Textfield_Text.getText());
        salt = String.valueOf(Textfield_salt.getText());
        String Algorithm = String.valueOf(Switch.getText());
        String answer="";
        switch (Algorithm) {
            case "MD5":
                answer=hashText("MD5",salt,message);
                Answer.setText(answer);
                break;
            case "SHA-256":
                answer=hashText("SHA-256",salt,message);
                Answer.setText(answer);
                break;
            case "SHA-512":
                answer=hashText("SHA-512",salt,message);
                Answer.setText(answer);
                break;
        }
    }







    public void switchAlgho(View view) {
        reset(null);
        String SwitchValue = Switch.getText().toString();
        switch (SwitchValue) {
            case "MD5":
                Switch.setText("SHA-256");
                break;
            case "SHA-256":
                Switch.setText("SHA-512");
                break;
            case "SHA-512":
                Switch.setText("MD5");
                break;
        }
    }



    public  String hashText(String algo,String salt, String plainText)
            throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance(algo);
        m.reset();
        if (salt.length() != 0) {
            m.update(salt.getBytes());
        }
        m.update(plainText.getBytes());
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1,digest);
        String hashtext = bigInt.toString(16);
        // Now we need to zero pad it if you actually want the full 32 chars.
        while(hashtext.length() < 32 ){
            hashtext = "0"+hashtext;
        }
        return hashtext;
    }



    private  byte[] getRandomSalt() throws NoSuchAlgorithmException, NoSuchProviderException
    {
        //Always use a SecureRandom generator
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        //Create array for salt
        byte[] salt = new byte[16];
        //Get a random salt
        sr.nextBytes(salt);
        //return salt
        return salt;
    }


    @SuppressLint("SuspiciousIndentation")
    public void reset(View view) {
        Textfield_Text.setText("");
        Textfield_salt.setText("");
        Answer.setText("");
        if(view!=null)
        Toast.makeText(view.getContext(), "All data has been deleted", Toast.LENGTH_SHORT).show();
    }



    public void copyToClipboard(View view) {
            String copyText = String.valueOf(Answer.getText());
            if (Answer.length() == 0) {
                Toast.makeText(view.getContext(), "There is no message to copy", Toast.LENGTH_SHORT).show();
                return;
            }

            int sdk = android.os.Build.VERSION.SDK_INT;
            if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
                android.text.ClipboardManager clipboard = (android.text.ClipboardManager)
                        view.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                clipboard.setText(copyText);
            } else {
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager)
                        view.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clip = android.content.ClipData
                        .newPlainText("Your message :", copyText);
                clipboard.setPrimaryClip(clip);
            }
            Toast.makeText(view.getContext(),
                    "Your message has be copied", Toast.LENGTH_SHORT).show();
        }
    }























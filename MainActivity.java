package eecs1022.lab8.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import eecs1022.lab8.tictactoe.model.Game;

public class MainActivity extends AppCompatActivity {

    /* Hint: How do you share the same game object between button clicks
     * (attached with controller methods) of the app?
     */
    Game g; boolean clicked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Hint: How do you display the initial status to the output textview
         * when the app is first launched?
         */
        setContentsOfTextView(R.id.result, "No game has been started.");
    }

    /* this mutator sets the output label */
    private void setContentsOfTextView(int id, String newContents) {
        View view = findViewById(id);
        TextView textView = (TextView) view;
        textView.setText(newContents);
    }

    /* this accessor retrieves input entered on the text view  */
    private String getInputOfTextField(int id) {
        View view = findViewById(id);
        EditText editText = (EditText) view;
        String input = editText.getText().toString();
        return input;
    }

    /* this accessor retrieves input chosen from some spinner (drop-down menu) */
    private String getItemSelected(int id) {
        View view = findViewById(id);
        Spinner spinner = (Spinner) view;
        String string = spinner.getSelectedItem().toString();
        return string;
    }

    /* Hints on controller methods:
     *
     * Declare two controller methods, each of which declared with a parameter with type View (see Week 9 Tutorials).
     *  - One method (say cm1) should be attached to the "START/RESTART" button,
     *      whereas the other method (say cm2) should be attached to the "MOVE" button.
     *
     *  - Controller method cm1 should:
     *    + Retrieve the names of the two players from the corresponding textfields.
     *    + Retrieve the player (who will play first) from the spinner.
     *    + Then, re-create the shared game object and invoke the relevant method(s).
     *    + Finally, display the expected output of the game (i.e., board and status) to the output textview.
     *
     * - Controller method cm2 should:
     *    + Retrieve the row and column numbers (as strings) from the corresponding textfields.
     *      You may need to convert the retrieved text, e.g., "1" to an integer value using Double.parseInt.
     *    + Then, invoke the relevant method(s) on the shared game object.
     *    + Finally, display the expected output of the game (i.e., board and status) to the ouptut textview.
     */

    public void cm1(View view){
        String name = getInputOfTextField(R.id.editTextTextPersonName);
        String name2 = getInputOfTextField(R.id.editTextTextPersonName2);

        String option = getItemSelected(R.id.spinner);
        g = new Game(name, name2);
        if(option.equals("Player X")) g.setWhoPlaysFirst('x');
        else if(option.equals("Player O")) g.setWhoPlaysFirst('o');

        String board = "";
        for(int i = 0; i<g.getBoard().length; i++){
            for(int j = 0; j<g.getBoard()[i].length; j++){
                board += ""+g.getBoard()[i][j]+" ";
            }
            board += "\n";
        }
        setContentsOfTextView(R.id.result, "Current Game Board: \n"+board+"Current Game Status: \n"+g.getStatus());
        clicked = true;
    }

    public void cm2(View view){
        if(clicked){
            try {
                int rows = Integer.parseInt(getInputOfTextField(R.id.editTextTextPersonName3));
                int cols = Integer.parseInt(getInputOfTextField(R.id.editTextTextPersonName4));

                String board = "";
                g.move(rows, cols);
                for(int i = 0; i<g.getBoard().length; i++){
                    for(int j = 0; j<g.getBoard()[i].length; j++){
                        board += ""+g.getBoard()[i][j]+" ";
                    }
                    board += "\n";
                }
                setContentsOfTextView(R.id.result, "Current Game Board: \n"+board+"Current Game Status: \n"+g.getStatus());

            }catch (NumberFormatException e) {}
        }
    }

}
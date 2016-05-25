package com.rdupuis.amikcal2.energy;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rdupuis.amikcal2.Qty.Qty_Manager;
import com.rdupuis.amikcal2.R;
import com.rdupuis.amikcal2.commons.RETURNCODE;
import com.rdupuis.amikcal2.commons.numericpad.Act_NumericPad;
import com.rdupuis.amikcal2.components.Component_List_Builder;
import com.rdupuis.amikcal2.components.food.Component_Food;
import com.rdupuis.amikcal2.components.food.Component_Food_Manager;
import com.rdupuis.amikcal2.scenes.Scene01;
import com.rdupuis.amikcal2.unity.Act_UnitOfMeasureList;
import com.rdupuis.amikcal2.unity.Unity;
import com.rdupuis.amikcal2.unity.Unity_Manager;
import com.rdupuis.gamingtools.components.OpenGLActivity;
import com.rdupuis.gamingtools.components.Scene;
import com.rdupuis.gamingtools.components.keyboard.Keyboard;

import java.util.ArrayList;

/**
 * Cette vue permet de renseigner une nouvelle energie et ses equivalences
 *
 * @author Rodolphe
 */
public class Act_Food_Editor extends OpenGLActivity {

    Food mFood;
    private ListView maListViewPerso;
    private ArrayList<Component_Food> componentFoodList;
    public static final String INPUT____FOOD = "INPUT_FOOD";

    public final static int GET_KEYPRESSED = 1;
    public String input_string_data;

    // ! OpenGL SurfaceView

    private Scene mScene;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, this.getClass().getCanonicalName(), Toast.LENGTH_SHORT).show();

        mFood = getIntent().getExtras().getParcelable(INPUT____FOOD);

        Component_List_Builder builder = new Component_List_Builder(this);
        componentFoodList = builder.getComponent_Food_List(mFood);

        if (componentFoodList.isEmpty()) {
            Component_Food energeticValue = new Component_Food();
            componentFoodList.add(energeticValue);
        }

        //initialisation du Handler
        initHandler();

        //définition de la scène à rendre
        mGLSurfaceView.setRenderer(new Scene01(this));

        EditText editbox = new EditText(this);

        editbox.setText("hello les amis");

        editbox.setTextColor(Color.WHITE);

        addContentView(editbox, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        //j'ajoute la vue par dessus
        View toto = getLayoutInflater().inflate(R.layout.view_edit_energy_food2, null);
        addContentView(toto, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));


/**
 ((TextView) findViewById(R.id.energyview_edTxt_energy_name)).addTextChangedListener(new TextWatcher() {
 public void onTextChanged(CharSequence s, int start, int before, int count) {
 mFood.setName(s.toString());
 }

 public void afterTextChanged(Editable arg0) {
 // TODO Auto-generated method stub

 }

 public void beforeTextChanged(CharSequence s, int start, int count, int after) {
 // TODO Auto-generated method stub

 }

 });

 refreshScreen();
 */
    }// fin du onCreate


    private void initHandler() {
        //je crée un Handler et je reféfini la méthose handleMessage
        //de cette manière, je peux capter des informations qui sont émises par d'autres
        //thread pour pouvoir effectuer des actions dans cette scène.
        //notamemnt, la mise à jour des View (textView...etc..)
        //car seul le Thread de la scène peu mettre à jour les vue de la scène
        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message inputMessage) {
                // Gets the image task from the incoming Message object.
                //PhotoTask photoTask = (PhotoTask) inputMessage.obj;

                switch (inputMessage.what) {

                    case GET_KEYPRESSED:

                        TextView tv = (TextView)findViewById(R.id.energyview_edTxt_energy_name);
                        String current_text = tv.getText().toString();
                        char c = inputMessage.getData().getChar(Keyboard.KEYPRESSED);

                        tv.setText(tv.getText()+String.valueOf(c));


                        break;
                    default:
//                        onClick_bt_amount();

                }

            }
        };

    }


    /***************************************************************************************
     * @param v
     **************************************************************************************/
    public void onClick_bt_amount(View v) {
        Intent intent = new Intent(this, Act_NumericPad.class);
        intent.putExtra("question", "Entrez la quantit� de r�f�rence");
        startActivityForResult(intent, R.integer.NUMERICPAD);
    }


    /***************************************************************************************
     * @param
     **************************************************************************************/
    public void onClick_bt_amount() {
        Intent intent = new Intent(this, Act_NumericPad.class);
        intent.putExtra("question", "Entrez la quantit� de r�f�rence");
        startActivityForResult(intent, R.integer.NUMERICPAD);
    }


    /***************************************************************************************
     * Quand on clique sur le bouton des unitées, on affiche une liste de choix
     *
     * @param v
     **************************************************************************************/
    public void onClick_bt_unity(View v) {
        Intent intent = new Intent(this, Act_UnitOfMeasureList.class);
        intent.putExtra(Act_UnitOfMeasureList.INPUT____ENERGY_FILTER, this.mFood);

        //Start de l'activity
        startActivityForResult(intent, R.integer.ACTY_UNITS_LIST);


    }
/*
    public void onClick_RdioBt_Liquid(View v) {
        this.mFood.setStructure(STRUCTURE.LIQUID);
        ((CompoundButton) findViewById(R.id.rdiobt_liquid)).setChecked(true);
        ((CompoundButton) findViewById(R.id.rdiobt_solid)).setChecked(false);
        ((CompoundButton) findViewById(R.id.rdiobt_powder)).setChecked(false);
    }

    public void onClick_RdioBt_Solid(View v) {
        this.mFood.setStructure(STRUCTURE.SOLID);
        ((CompoundButton) findViewById(R.id.rdiobt_liquid)).setChecked(false);
        ((CompoundButton) findViewById(R.id.rdiobt_solid)).setChecked(true);
        ((CompoundButton) findViewById(R.id.rdiobt_powder)).setChecked(false);
    }

    public void onClick_RdioBt_Powder(View v) {
        this.mFood.setStructure(STRUCTURE.POWDER);
        ((CompoundButton) findViewById(R.id.rdiobt_liquid)).setChecked(false);
        ((CompoundButton) findViewById(R.id.rdiobt_solid)).setChecked(false);
        ((CompoundButton) findViewById(R.id.rdiobt_powder)).setChecked(true);
    }
*/

    /*******************
     * ***********************************************************************
     * onActivityResult : on récupère les info saisies dans le pad numérique.
     ******************************************************************************************/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        switch (requestCode) {

            case R.integer.NUMERICPAD:

                if (resultCode == RESULT_OK) {

                    this.mFood.getQtyReference().setAmount(intent.getFloatExtra(Act_NumericPad.OUTPUT____AMOUNT, 0f));
                }
                break;

            case R.integer.ACTY_UNITS_LIST:

                if (resultCode == RESULT_OK) {

                    Unity unity = intent.getExtras().getParcelable(Act_UnitOfMeasureList.OUTPUT____CHOOSED_UNIT);
                    this.mFood.getQtyReference().setUnity(unity);


                }
                break;
            default:
                break;

        }

        refreshScreen();
    }

    /******************************************************************************************
     * onValidateClick : Mettre à jour les informations saisies dans la base de
     * donnée
     *
     * @param v
     ******************************************************************************************/
    public void onClick_Validate(View v) {

        //récupérer le libéllé de l'énergie
        EditText ed = (EditText) findViewById(R.id.energyview_edTxt_energy_name);
        this.mFood.setName(ed.getText().toString());

        //Sauver la Qty de référence et récupérer l'ID de la Database
        Qty_Manager qty_manager = new Qty_Manager(this);
        long newQtyId = qty_manager.save(this.mFood.getQtyReference());
        this.mFood.getQtyReference().setDatabaseId(newQtyId);

        //Enregister l'énergie Food
        Energy_Food_Manager energy_food_manager = new Energy_Food_Manager(this);
        this.mFood.setDatabaseId(energy_food_manager.save(this.mFood));


        // On termine l'Actvity si tout c'est bien passé
        if (energy_food_manager.getReturnCode() == RETURNCODE.OK) {
            // on appelle setResult pour déclancher le onActivityResult de l'activity mère.
            setResult(RESULT_OK, getIntent());
            finish();
        }
    }


    public void onClick_Add_Equiv(View view) {
        Unity_Manager unity_manager = new Unity_Manager(this);
        Unity unity = unity_manager.load(R.integer.Kcal_id);

        Component_Food component_food = new Component_Food();
        component_food.getQty().setUnity(unity);
        Component_Food_Manager manager = new Component_Food_Manager(this);
        manager.edit(component_food);


    }

    /***************************************************************************************
     *

     **************************************************************************************/
    private void refreshScreen() {
/*
        DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getInstance();
        decimalFormat.applyPattern("#,##0.00");
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator(',');
        decimalFormat.setDecimalFormatSymbols(dfs);

        Button bt = (Button) findViewById(R.id.energyview_btn_amount);
        bt.setText(decimalFormat.format(this.mFood.getQtyReference().getAmount()));

        bt = (Button) findViewById(R.id.energyview_btn_unity);
        bt.setText(this.mFood.getQtyReference().getUnity().getLongName());

        EditText ed = (EditText) findViewById(R.id.energyview_edTxt_energy_name);
        ed.setText(this.mFood.getName());

        switch (this.mFood.getStructure()) {
            case LIQUID:
                ((CompoundButton) findViewById(R.id.rdiobt_liquid)).setChecked(true);
                ((CompoundButton) findViewById(R.id.rdiobt_solid)).setChecked(false);
                ((CompoundButton) findViewById(R.id.rdiobt_powder)).setChecked(false);
                break;
            case SOLID:
                ((CompoundButton) findViewById(R.id.rdiobt_liquid)).setChecked(false);
                ((CompoundButton) findViewById(R.id.rdiobt_solid)).setChecked(true);
                ((CompoundButton) findViewById(R.id.rdiobt_powder)).setChecked(false);
                break;
            case POWDER:
                ((CompoundButton) findViewById(R.id.rdiobt_liquid)).setChecked(false);
                ((CompoundButton) findViewById(R.id.rdiobt_solid)).setChecked(false);
                ((CompoundButton) findViewById(R.id.rdiobt_powder)).setChecked(true);
            default:
                ((CompoundButton) findViewById(R.id.rdiobt_liquid)).setChecked(false);
                ((CompoundButton) findViewById(R.id.rdiobt_solid)).setChecked(false);
                ((CompoundButton) findViewById(R.id.rdiobt_powder)).setChecked(false);

                break;
        }

        // Récupération de la listview créée dans le fichier customizedlist.xml
        maListViewPerso = (ListView) findViewById(R.id.listviewperso);

        // effacer la liste actuelle
        maListViewPerso.removeAllViewsInLayout();

        // Création de la ArrayList qui nous permettra de remplir la listView
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();

        // On déclare la HashMap qui contiendra les informations pour un item
        HashMap<String, String> map;
        map = new HashMap<String, String>();

        // Pour chaque �quivalence au composant de r�f�rence..


        for (Component_Food componentFood : this.componentFoodList) {

            map = new HashMap<String, String>();

            map.put("quantity", String.valueOf(((Component) componentFood).getQty().getAmount()));
            map.put("unity", ((Component) componentFood).getQty().getUnity().getLongName());
            map.put("name", ((Component) componentFood).getEnergy().getName());

            listItem.add(map);
        }

        // Création d'un SimpleAdapter qui se chargera de mettre les items
        // présent dans notre list (listItem)
        // dans la vue affichageitem
        SimpleAdapter mSchedule = new SimpleAdapter(this.getBaseContext(), listItem,
                R.layout.list_item_activity_lunch_component, new String[]{"name", "quantity", "unity", "equiv",
                "nbglu", "nbpro", "nblip"}, new int[]{R.id.itemfood_name, R.id.itemfood_quantity,
                R.id.itemfood_nbkcal, R.id.itemfood_equiv, R.id.itemfood_glu, R.id.itemfood_pro,
                R.id.itemfood_lip});

        // On attribut à notre listView l'adapter que l'on vient de créer
        maListViewPerso.setAdapter(mSchedule);
*/
    }

    /***************************************************************************************
     * @param v
     **************************************************************************************/
    public void onClick_Cancel(View v) {
        finish();
    }

}
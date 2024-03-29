package com.abusiness.contactosbd

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var emailEditText:EditText
    private lateinit var provinEditText: EditText
    private lateinit var buscaprovEditText:EditText
    private lateinit var salvar:Button
    private lateinit var consltaButton:Button
    private lateinit var bosquejo:TextView
    private lateinit var db:DatabaseHander
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nameEditText = findViewById(R.id.editTextText)
        emailEditText = findViewById(R.id.editTextText2)
        provinEditText = findViewById(R.id.editTextText3)
        buscaprovEditText = findViewById(R.id.editTextText4)
        consltaButton = findViewById(R.id.button2)
        bosquejo = findViewById(R.id.textView)
        salvar = findViewById(R.id.button)

        //todo llama a la clase para acceder a todos los metodos de la clase
        db= DatabaseHander(this)  // todo instancia la clase DatabaseHander

        //todo ponemos el escuchador del boton
        salvar.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val provincia = provinEditText.text.toString().trim()
            if  (name.isNotEmpty() && email.isNotEmpty()){
                val id=db.addContact(name,email,provincia) // todo devuelve una referencia exitosa o no
                if (id == -1L){
                    // todo aqui iria un toast avisando de error
                    Toast.makeText(applicationContext, "guardado", Toast.LENGTH_SHORT).show()
                }
                    else{
                    nameEditText.text.clear()
                    emailEditText.text.clear()
                    provinEditText.text.clear()
                       }
            }else{
                // todo ha habido un problema en la base de datos
               Toast.makeText(applicationContext, "te falta algun campo", Toast.LENGTH_SHORT).show()
            }
        }  // todo fin de salvar
        consltaButton.setOnClickListener {
            bosquejo.text="" // todo aqui borramos primero el textview por si le damos directamente al boton buscar y que no se amontonen otras busquedas
            val contacList:List<Contact> = db.getAllContacts()  // es de tipo List<Contact> si no se lo pones lo infiere, pero NO es un String
            // todo este for ya no hace falta porque tenemos el joinToString()
            // implementado en la siguiente variable
            for (contact in contacList){
                contact.name
                contact.email
                contact.provincia
                bosquejo.append(contact.name+" "+contact.email+" "+contact.provincia+"\n") // todo sustituimos el .text por el append
                //todo para que en lugar de sobreescribir añada
              //  if(contact.id==variableBusqueda){}

                }
              //  Log.d("Contacts","ID: ${contact.id}, Nombre: ${contact.name}, Email: ${contact.email}")

            /*
            val resultadoBD =contacList.joinToString()  //todo este joinToString() recorre toda la coleccion
                                                        //todo y lo esta metiendo en la variable
                                                       // todo el joinToString() lo cambia el List<Contact> a String
            bosquejo.text=resultadoBD
            */

        }// todo fin de consultabutton
    }
}
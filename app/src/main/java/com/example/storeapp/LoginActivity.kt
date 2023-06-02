package com.example.storeapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.sql.DriverManager
import java.sql.SQLException

class LoginActivity : AppCompatActivity() {
    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button

    private val dbUrl = "jdbc:mysql://localhost:3307/storeapp"
    private val dbUser = "root"
    private val dbPassword = ""

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextUsername = findViewById(R.id.editTextUsername)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)

        buttonLogin.setOnClickListener {
            val username = editTextUsername.text.toString()
            val password = editTextPassword.text.toString()

            if (validateCredentials(username, password)) {
                // Credenciales válidas, iniciar sesión
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
                Toast.makeText(applicationContext, "Inicio de Sesión Correcto", Toast.LENGTH_LONG).show()
            } else {
                // Credenciales inválidas, mostrar mensaje de error
                Toast.makeText(applicationContext, "Credenciales Incorrectas", Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun validateCredentials(username: String, password: String): Boolean {
        // Aquí implementa la lógica para validar las credenciales
        // Puedes hacer consultas a la base de datos o utilizar cualquier otro mecanismo de validación
        // Retorna true si las credenciales son válidas, de lo contrario, retorna false

        // Ejemplo de validación básica
        val validUsername = "Maycol"
        val validPassword = "12345"
        return username == validUsername && password == validPassword
    }
}



/*    private fun validateCredentials(username: String, password: String): Boolean {
        try {
            // Establecer la conexión con la base de datos
            val connection = DriverManager.getConnection(dbUrl, dbUser, " ")
            Toast.makeText(applicationContext, "Conexion establecida", Toast.LENGTH_LONG).show()

            // Crear la sentencia SQL para buscar el usuario y contraseña en la tabla
            val query = "SELECT COUNT(*) FROM usuario WHERE usuario = ? AND password = ?"
            val statement = connection.prepareStatement(query)
            statement.setString(1, username)
            statement.setString(2, password)

            // Ejecutar la consulta
            val resultSet = statement.executeQuery()

            // Verificar si se encontró una coincidencia en la base de datos
            val count = if (resultSet.next()) resultSet.getInt(1) else 0

            // Cerrar los recursos
            resultSet.close()
            statement.close()
            connection.close()

            // Retornar true si se encontró una coincidencia, de lo contrario, false
            return count > 0
        } catch (e: SQLException) {
            e.printStackTrace()
            // Manejar cualquier error de conexión o consulta
            Toast.makeText(applicationContext, "Error al conectar a la base de datos", Toast.LENGTH_LONG).show()
        }

        return false
    }
}*/

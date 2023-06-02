package com.example.storeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class MainActivity : AppCompatActivity() {

    private lateinit var connection: Connection
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)

        // Configurar el ActionBarDrawerToggle para abrir y cerrar el Navigation Drawer
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Establecer el listener para los elementos de menú seleccionados
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_item1 -> showFragment(MenuFragment())
                R.id.menu_item2 -> showFragment(PlatosFragment())
                R.id.menu_item3 -> showFragment(PedidosFragment())
                R.id.menu_item_logout -> logout()
            }
            // Cerrar el Navigation Drawer después de seleccionar un elemento
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        // Mostrar el fragmento inicial al iniciar la actividad
        showFragment(MenuFragment())
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.contentFrame, fragment)
            .commit()
    }

    private fun logout() {
        // Aquí puedes realizar cualquier lógica adicional antes de cerrar la sesión

        // Navegar de vuelta a la LoginActivity
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
        Toast.makeText(applicationContext, "Cesion Cerrada", Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        // Cerrar el Navigation Drawer al presionar el botón de retroceso si está abierto
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

/*    private fun connectToDatabase() {
        val url = "jdbc:mysql://localhost:3307/storeapp"
        val username = "root"
        val password = ""

        try {
            Class.forName("com.mysql.jdbc.Driver")
            connection = DriverManager.getConnection(url, username, password)
            // La conexión ha sido establecida correctamente
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
            // Error al cargar el controlador de la base de datos
        } catch (e: SQLException) {
            e.printStackTrace()
            // Error al establecer la conexión con la base de datos
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            connection.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }*/

}
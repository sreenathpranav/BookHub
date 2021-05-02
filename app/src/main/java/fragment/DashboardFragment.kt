package fragment

import adapter.DashboardRecyclerAdapter
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.internshala.bookhub.R
import model.Book
import util.ConnectionManager


class DashboardFragment : Fragment() {

    lateinit var recyclerDashboard: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var btnCheckInternet: Button



    )
    lateinit var recyclerAdapter: DashboardRecyclerAdapter

    val bookInfoList = arrayListOf<Book>(

        /*Book("P.S. I love You", "Cecelia Ahern", "Rs. 299", "4.5", R.drawable.ps_ily),
         Book("The Great Gatsby", "F. Scott Fitzgerald", "Rs. 399", "4.1", R.drawable.great_gatsby),
         Book("Anna Karenina", "Leo Tolstoy", "Rs. 199", "4.3", R.drawable.anna_kare),
         Book("Madame Bovary", "Gustave Flaubert", "Rs. 500", "4.0", R.drawable.madame),
         Book("War and Peace", "Leo Tolstoy", "Rs. 249", "4.8", R.drawable.war_and_peace),
         Book("Lolita", "Vladimir Nabokov", "Rs. 349", "3.9", R.drawable.lolita),
         Book("Middlemarch", "George Eliot", "Rs. 599", "4.2", R.drawable.middlemarch),
         Book(
             "The Adventures of Huckleberry Finn",
             "Mark Twain",
             "Rs. 699",
             "4.5",
             R.drawable.adventures_finn
         ),
         Book("Moby-Dick", "Herman Melville", "Rs. 499", "4.5", R.drawable.moby_dick),
         Book("The Lord of the Rings", "J.R.R Tolkien", "Rs. 749", "5.0", R.drawable.lord_of_rings)
     )


     )*/

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
            recyclerDashboard = view.findViewById(R.id.recyclerDashboard)

            btnCheckInternet = view.findViewById(R.id.btnCheckInternet)
            btnCheckInternet.setOnClickListener {
                if (ConnectionManager().checkConnectivity(activity as Context)) {
                    val dialog = AlertDialog.Builder(activity as Context)
                    dialog.setTitle("Success")
                    dialog.setMessage("Internet Connection Found")
                    dialog.setPositiveButton("ok") { text ->
                    }
                    dialog.setNegativeButton("Cancel") { text, listener ->
                    }
                    dialog.create()
                    dialog.show()

                } else {
                    val dialog = AlertDialog.Builder(activity as Context)
                    dialog.setTitle("Error")
                    dialog.setMessage("Internet Connection not Found")
                    dialog.setPositiveButton("ok") { text ->
                    }
                    dialog.setNegativeButton("Cancel") { text, listener ->
                    }
                    dialog.create()
                    dialog.show()


                }
            }

            layoutManager = LinearLayoutManager(activity)

            val queue = Volley.newRequestQueue(activity as Context)
            val url = "http ://13.235.250.119/vi/book/fetch_books/"

            if (ConnectionManager().checkConnectivity(activity as Context)) {
                val jsonObjectRequest =
                    object : JsonObjectRequest(Method.GET, url, null, Response.Listener {

                        try
                        {
                            val success = it.getBoolean("success")

                            if (success) {
                                val data = it.getJSONArray("data")

                                for (i in 0 until data.length()) {
                                    val bookJsonObject = data.getJSONObject(i)
                                    val bookObject = Book(
                                        bookJsonObject.getString("Book_id")
                                                bookJsonObject . getString ("name")
                                                bookJsonObject . getString ("author")
                                                bookJsonObject . getString ("rating")
                                                bookJsonObject . getString ("price")
                                                bookJsonObject . getString ("image")
                                    )
                                    bookInfoList.add(bookObject)
                                    recyclerAdapter = adapter.DashboardRecyclerAdapter(
                                        activity as android.content.Context,
                                        bookInfoList
                                    )

                                    recyclerDashboard.adapter = recyclerAdapter
                                    recyclerDashboard.layoutManager = layoutManager
                                    recyclerDashboard.addItemDecoration(
                                        androidx.recyclerview.widget.DividerItemDecoration(
                                            recyclerDashboard.context,
                                            (layoutManager as androidx.recyclerview.widget.LinearLayoutManager).orientation
                                        )
                                    )
                                }
                            } else {
                                android.widget.Toast.makeText(
                                    activity as Context,
                                    "Some error Occured!!!",
                                    android.widget.Toast.LENGTH_SHORT
                                ).show()
                        }catch(e: JSONException)
                            {
                                android.widget.Toast.makeText(activity as Context,"Some Error Occured!!!",   android.widget.Toast.LENGTH_SHORT).show())
                            }

                        }, Response.ErrorListener {
                        android.widget.Toast.makeText(activity as android.content.Context,"Volley error occured",   android.widget.Toast.LENGTH_SHORT).show())
                    }

                    }
                        ) {
                        overide
                        fun getHeader(): MutableMap<String, String> {
                            val headers = HashMap<String, String>()
                            headers["Content-Type"] = "application/json"
                            headers["token"] = "2e1cf229ed27ed"
                            return headers
                            val jsonObjectRequest =
                                object :
                                    JsonObjectRequest(Method.GET, url, null, Response.Listener {

                                        val success = it.getBoolean("success")

                                        if (success) {
                                            val data = it.getJSONArray("data")

                                            for (i in 0 until data.length()) {
                                                val bookJsonObject = data.getJSONObject(i)
                                                val bookObject = Book(
                                                    bookJsonObject.getString("Book_id")
                                                            bookJsonObject . getString ("name")
                                                            bookJsonObject . getString ("author")
                                                            bookJsonObject . getString ("rating")
                                                            bookJsonObject . getString ("price")
                                                            bookJsonObject . getString ("image")
                                                )
                                                bookInfoList.add(bookObject)
                                                recyclerAdapter = adapter.DashboardRecyclerAdapter(
                                                    activity as android.content.Context,
                                                    bookInfoList
                                                )

                                                recyclerDashboard.adapter = recyclerAdapter
                                                recyclerDashboard.layoutManager = layoutManager
                                                recyclerDashboard.addItemDecoration(
                                                    androidx.recyclerview.widget.DividerItemDecoration(
                                                        recyclerDashboard.context,
                                                        (layoutManager as androidx.recyclerview.widget.LinearLayoutManager).orientation
                                                    )
                                                )
                                            }
                                        } else {
                                            android.widget.Toast.makeText(
                                                activity as Context,
                                                "Some error Occured!!!",
                                                android.widget.Toast.LENGTH_SHORT
                                            ).show()
                                        }, Response.ErrorListener {

                                    }
                                        ) {
                                        overide
                                        fun getHeader(): MutableMap<String, String> {
                                            val headers = HashMap<String, String>()
                                            headers["Content-Type"] = "application/json"
                                            headers["token"] = "2e1cf229ed27ed"
                                            return headers

                                        }
                                    }
                                        queue.add(com.android.volley.toolbox.JsonObjectRequest)
                                    }else {
                                    val dialog = AlertDialog.Builder(activity as Context)
                                    dialog.setTitle("Error")
                                    dialog.setMessage("Internet Connection not Found")
                                    dialog.setPositiveButton(Open Settings") { text ->
                                }
                            dialog.setNegativeButton("Cancel") { text, listener ->
                            }
                            dialog.create()
                            dialog.show()


                        }
                    }  return view
                    }


            }
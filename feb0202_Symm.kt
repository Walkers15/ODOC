package boj.kotlin

fun main() {
    println("20200202")
    println("Today is Symmetric Day!")
    val snowflake : String = """
                        ()
                        /\
                       //\\
                      <<  >>
                  ()   \\//   ()
        ()._____   /\   \\   /\   _____.()
           \.--.\ //\\ //\\ //\\ /.--./
            \\__\\/__\//__\//__\\/__//
             '--/\\--//\--//\--/\\--'
                \\\\///\\//\\\////
            ()-= >>\\< <\\> >\\<< =-()
                ////\\\//\\///\\\\
             .--\\/--\//--\//--\//--.
            //""/\\""//\""//\""//\""\\
           /'--'/ \\// \\// \\// \'--'\
         ()`""${'"'}`   \/   //   \/   `""${'"'}${'"'}`()
                  ()   //\\   ()
                      <<  >>
                       \\//
                        \/
                        ()
    """.trimIndent()
    println(snowflake)
}

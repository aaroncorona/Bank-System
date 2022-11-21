module com.example.aaroncorona_cs56_proj8 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.aaroncorona_cs56_proj8 to javafx.fxml;
    exports com.example.aaroncorona_cs56_proj8;
}
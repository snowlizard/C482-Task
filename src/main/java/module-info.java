module wgu.jgon.c482.task {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens wgu.jgon.c482.task to javafx.fxml;
    exports wgu.jgon.c482.task;
}

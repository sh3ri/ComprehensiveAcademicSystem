package Controller;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import mainpackage.Main;

public class WindowMover {
    private Node node;

    public WindowMover(Node node) {
        this.node = node;
    }

    public void setDragListener() {
        final Delta dragDelta = new Delta();
        node.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // record a delta distance for the drag and drop operation.
                dragDelta.x = Main.primaryStage.getX() - mouseEvent.getScreenX();
                dragDelta.y = Main.primaryStage.getY() - mouseEvent.getScreenY();
            }
        });
        node.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Main.primaryStage.setX(mouseEvent.getScreenX() + dragDelta.x);
                Main.primaryStage.setY(mouseEvent.getScreenY() + dragDelta.y);
            }
        });


    }
}

// records relative x and y co-ordinates.
class Delta {
    double x, y;
}

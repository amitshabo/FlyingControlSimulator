package view;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

class DraggableNode extends Pane {

    // node position
    private double x = 0;
    private double y = 0;
    // mouse position
    private double mousex = 0;
    private double mousey = 0;
    private Node view;
    private boolean dragging = false;
    private boolean moveToFront = true;

    public DraggableNode() {
        init();
    }

    public DraggableNode(Node view) {
        this.view = view;

        getChildren().add(view);
        init();
    }

    private void init() {

        onMousePressedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                // record the current mouse X and Y position on Node
                mousex = event.getSceneX();
                mousey = event.getSceneY();
                System.out.println("mousex: "+mousex);
                System.out.println("mousey: "+mousey);

                x = getLayoutX();
                y = getLayoutY();
                System.out.println("x = getLayoutX: "+x);
                System.out.println("y = getLayoutY "+y);

                if (isMoveToFront()) {
                    toFront();
                }
            }
        });

        
        //On Mouse Dragged Done
        onMouseReleasedProperty().set(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				setLayoutX(0.0);
				setLayoutY(0.0);
				System.out.println("Realesedddd!!!!");
			}
        	
		});
        
        //Event Listener for MouseDragged
        onMouseDraggedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                // Get the exact moved X and Y

                double offsetX = event.getSceneX() - mousex;
                double offsetY = event.getSceneY() - mousey;
                
                System.out.println("offsetX: "+event.getSceneX());
                System.out.println("offsetY: "+event.getSceneY());

//                double dd = Math.sqrt(((x-0)*(x-0))+((y-0)*(y-0)));
//                
//                if(dd<100) {
                    x += offsetX;
                    y += offsetY;
//                }

                

                double scaledX = x;
                double scaledY = y;
                

//                System.out.println("distance: "+dd);
                
                setLayoutX(scaledX);
                setLayoutY(scaledY);

                dragging = true;

                // again set current Mouse x AND y position
                mousex = event.getSceneX();
                mousey = event.getSceneY();

                event.consume();
            }
        });

        onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                dragging = false;
            }
        });

    }

    /**
     * @return the dragging
     */
    protected boolean isDragging() {
        return dragging;
    }


    /**
     * @return the view
     */
    public Node getView() {
        return view;
    }

    /**
     * @param moveToFront the moveToFront to set
     */
    public void setMoveToFront(boolean moveToFront) {
        this.moveToFront = moveToFront;
    }

    /**
     * @return the moveToFront
     */
    public boolean isMoveToFront() {
        return moveToFront;
    }
    
    public void removeNode(Node n) {
        getChildren().remove(n);
    }
	@FXML
    public void initialize() {
		
	}
}
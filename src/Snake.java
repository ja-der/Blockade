public class Snake {

    int bodyParts, unitSize, screenWidth, screenHeight;
    char direction;
    final int x [], y [];


    Snake (int gridBlocks, int unitSize, int screenWidth, int screenHeight) {
        bodyParts = 1;
        direction = 'R';
        this.unitSize = unitSize;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        x = new int[gridBlocks];
        y = new int[gridBlocks];
    }

    public void grow () {
        bodyParts ++;
    }

    public boolean checkCollisions () {
        // check for collisions with itself
        for (int i = bodyParts; i > 0; i --){
            if ((x[0] == x[i]) && (y[0] == y[i])){
                return false;
            }
        }

        // check for border collisions
        if (x[0] < 0){
            return false;
        }
        if (x[0] > screenWidth){
            return false;        
        }
        if (y[0] < 0){
            return false;        
        }
        if (y[0] > screenHeight) {
            return false;       
        }
        return true;
    }

    public void move () {
        for (int i = bodyParts; i > 0; i --){
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction){
            case 'U':
                y[0] = y[0] - unitSize;
                break;
            case 'D':
                y[0] = y[0] + unitSize;
                break;
            case 'L':
                x[0] = x[0] - unitSize;
                break;
            case 'R':
                x[0] = x[0] + unitSize;
        
        }

    }
    
}

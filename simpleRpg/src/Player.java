class Player {
    private final String name;
    private boolean isAlive = true;
    private int healthPoints = 50;


    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void takeDamage(int dmg){
        if(dmg >= healthPoints){
            healthPoints = 0;
            isAlive = false;
        }
        else{
            healthPoints -= dmg;
        }
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public boolean isAlive(){
        return isAlive;
    }


}
package feup.ldts.trex.model.elements;

public class Dino {
    private int x;
    private int y;
    private int powerUp;
    private int jumpState;
    private int powerLength;
    private int animation;

    public Dino(int x, int y) {
        this.x = x;
        this.y = y;
        this.powerUp = 0;
        this.jumpState = 0;
        this.powerLength = 0;
        this.animation = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getJumpState() {
        return jumpState;
    }

    public void setJumpState(int state) {
        this.jumpState = state;
    }

    public void updateJumpState(){
        if (this.getJumpState()!=0){
            this.setJumpState(this.getJumpState()-1);
            if (this.getJumpState()==0){
                this.setY(this.getY()+1);
            }
        }
    }

    public int getPowerLength() {
        return powerLength;
    }

    public void setPowerLength(int frames) {
        this.powerLength = frames;
    }

    public int getPowerUp() {
        return powerUp;
    }

    public void setPowerUp(int powerUp) {
        this.powerUp = powerUp;
    }

    public void powerUsed() {
        if (getPowerUp()==1) setPowerLength(50);
        if (getPowerUp()==2) setPowerLength(2);
        if (getPowerUp()==3) setPowerLength(5);
    }

    public boolean updatePower(){
        boolean bonus = false;
        if (getPowerLength()!=0){
            setPowerLength(getPowerLength()-1);
            if (getPowerLength()==0){
                if (getPowerUp()==3) bonus = true;
                setPowerUp(0);
            }
        }
        return bonus;
    }

    public int getAnimation() {
        return animation;
    }

    public void setAnimation(int animation) {
        this.animation = animation;
    }

    public String updateDinosaurAnimation(){
        String anim;
        if (this.getAnimation()<=0) anim="T";
        else anim="t";
        if (this.getAnimation()<=-2) this.setAnimation(4);
        this.setAnimation(this.getAnimation()-1);
        return anim;
    }
}

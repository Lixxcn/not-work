public class Rgb{
    int rgbR;
    int rgbG;
    int rgbB;
    int x;
    int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRgbR() {
        return rgbR;
    }

    public void setRgbR(int rgbR) {
        this.rgbR = rgbR;
    }

    public int getRgbG() {
        return rgbG;
    }

    public void setRgbG(int rgbG) {
        this.rgbG = rgbG;
    }

    public int getRgbB() {
        return rgbB;
    }

    public void setRgbB(int rgbB) {
        this.rgbB = rgbB;
    }

    @Override
    public String toString() {
        String str = "x=" + this.getX() + ",y=" +this.getY() + ":(" + rgbR + "," + rgbG + "," + rgbB + ")";
        return str;
    }


    public boolean equals(Rgb obj) {
        if(obj.getRgbR() == rgbR && obj.getRgbG() == rgbG && obj.getRgbB() == rgbB){
            return true;
        }
        return false;
    }
}
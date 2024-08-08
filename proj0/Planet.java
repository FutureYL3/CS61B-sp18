public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        return Math.sqrt((this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) + (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos));
    }

    public double calcForceExertedBy(Planet p) {
        double r = this.calcDistance(p);
        return G * this.mass * p.mass / (r * r);
    }

    public double calcForceExertedByX(Planet p) {
        double r = this.calcDistance(p);
        double F = this.calcForceExertedBy(p);
        return F * (p.xxPos - this.xxPos) / r;
    }

    public double calcForceExertedByY(Planet p) {
        double r = this.calcDistance(p);
        double F = this.calcForceExertedBy(p);
        return F * (p.yyPos - this.yyPos) / r;
    }

    public double calcNetForceExertedByX(Planet[] ps) {
        double[] Fxs = new double[ps.length];
        for (int i = 0; i < ps.length; i++) {
            if (!this.equals(ps[i]))
                Fxs[i] = this.calcForceExertedByX(ps[i]);
            else
                Fxs[i] = 0;
        }
        double FxNet = 0;
        for (double fx : Fxs) {
            FxNet += fx;
        }
        return FxNet;
    }

    public double calcNetForceExertedByY(Planet[] ps) {
        double[] Fys = new double[ps.length];
        for (int i = 0; i < ps.length; i++) {
            if (!this.equals(ps[i]))
                Fys[i] = this.calcForceExertedByY(ps[i]);
            else
                Fys[i] = 0;
        }
        double FyNet = 0;
        for (double fx : Fys) {
            FyNet += fx;
        }
        return FyNet;
    }

    public void update(double dt, double Fx, double Fy) {
        double Xac = Fx / this.mass;
        double Yac = Fy / this.mass;
        this.xxVel += Xac * dt;
        this.yyVel += Yac * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}

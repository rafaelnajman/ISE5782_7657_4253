package primitives;

import java.util.Objects;

public class Ray {
    Point p0;
    Vector dir;

    // ***************** Constructors ********************** //
    public Ray(Point p0, Vector dir) {
        this.p0.xyz = p0.xyz;
        this.dir.xyz = dir.xyz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }
}




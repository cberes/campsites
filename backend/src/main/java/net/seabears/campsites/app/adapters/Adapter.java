package net.seabears.campsites.app.adapters;

public interface Adapter<S, D> {
    D adapt(S source);
}

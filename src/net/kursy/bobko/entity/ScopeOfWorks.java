package net.kursy.bobko.entity;


public class ScopeOfWorks extends Entity {

    private String scope;

    public ScopeOfWorks() {
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return scope;
    }
}

package org.practicalunittesting;

public class SUT {
    private Collaborator collaborator;
    public void setCollaborator(Collaborator collaborator) {
        this.collaborator = collaborator;
    }
    public String useCollaborator() {
        return collaborator.doSth();
    }
}

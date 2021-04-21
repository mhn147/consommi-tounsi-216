package tn.esprit.pidev.consommitounsi.entities.user;

public class EditPassword {
    private String oldPassword;
    private String newPassword;

    public EditPassword(){
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}

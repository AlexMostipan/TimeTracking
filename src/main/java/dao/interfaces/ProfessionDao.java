package dao.interfaces;

import model.Profession;

public interface ProfessionDao {
    public int create(Profession profession);

    public boolean update(Profession profession);

    public boolean delete(int id);
}

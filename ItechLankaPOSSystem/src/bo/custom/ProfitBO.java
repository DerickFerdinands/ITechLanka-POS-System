package bo.custom;

import Model.DirectorDTO;
import bo.SuperBO;

import java.util.ArrayList;

public interface ProfitBO extends SuperBO {

    ArrayList<DirectorDTO> getAllDirectors() throws Exception;

    double getProfit() throws Exception;
}

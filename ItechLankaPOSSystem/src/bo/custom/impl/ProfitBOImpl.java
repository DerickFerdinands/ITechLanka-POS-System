package bo.custom.impl;

import Model.DirectorDTO;
import bo.custom.ProfitBO;
import dao.Custom.DirectorDAO;
import dao.Custom.OrderDetailDAO;
import dao.DAOFactory;
import entity.Director;
import entity.OrderDetail;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ProfitBOImpl implements ProfitBO {

    private DirectorDAO dDAO = (DirectorDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.DIRECTOR);
    private OrderDetailDAO odDAO = (OrderDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER_DETAIL);
    @Override
    public ArrayList<DirectorDTO> getAllDirectors() throws Exception {
        ArrayList<Director> all = dDAO.getAll();
        return new ArrayList<DirectorDTO>(all.stream().map(director -> {
            return new DirectorDTO(director.getId(),director.getName(),director.getNic(),director.getMobile(),director.getAddress(),director.getProfitMargin());
        }).collect(Collectors.toList()));
    }

    @Override
    public double getProfit() throws Exception {
        return odDAO.getTotalProfit();
    }
}

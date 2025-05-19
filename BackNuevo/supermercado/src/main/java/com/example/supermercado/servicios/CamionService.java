package com.example.supermercado.servicios;

import java.util.Date;
import java.util.List;

import com.example.supermercado.modelo.dto.CamionDTO;


public interface CamionService {

	List<CamionDTO> obtenerCamiones();

	CamionDTO listarId(Long id);

	CamionDTO add(CamionDTO c);

	CamionDTO delete(Long id);

	List<CamionDTO> getCamionesByFechaCompraRange(Date fromDate, Date toDate);

	List<CamionDTO> findByFechaCompraAfter(Date desde);

	List<CamionDTO> findByFechaCompraBefore(Date hasta);
	
	List<CamionDTO> listaCamionesSinITV();
}

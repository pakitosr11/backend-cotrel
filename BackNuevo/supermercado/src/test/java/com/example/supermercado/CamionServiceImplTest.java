package com.example.supermercado;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.supermercado.mapper.CamionMapper;
import com.example.supermercado.modelo.dto.CamionDTO;
import com.example.supermercado.modelo.entity.Camion;
import com.example.supermercado.modelo.repositorio.CamionRepositorio;
import com.example.supermercado.servicios.CamionServiceImpl;



@ExtendWith(SpringExtension.class)
class CamionServiceImplTest {

    @Mock
    private CamionRepositorio camionRepositorio;

    @Mock
    private CamionMapper camionMapper;

    @InjectMocks
    private CamionServiceImpl camionService;

    @Test
    void obtenerCamiones() {
        // Arrange
        List<Camion> camiones = Arrays.asList(new Camion(), new Camion());
        List<CamionDTO> camionDTOS = Arrays.asList(new CamionDTO(), new CamionDTO());
        when(camionRepositorio.findAll()).thenReturn(camiones);
        when(camionMapper.listEntityToDto(camiones)).thenReturn(camionDTOS);

        // Act
        List<CamionDTO> resultado = camionService.obtenerCamiones();

        // Assert
        assertEquals(2, resultado.size());
    }

    @Test
    void listarIdCamionExistente() {
        // Arrange
        Long id = 1L;
        Camion camion = new Camion();
        CamionDTO camionDTO = new CamionDTO();
        when(camionRepositorio.findById(id)).thenReturn(Optional.of(camion));
        when(camionMapper.convertirACamionDTO(camion)).thenReturn(camionDTO);

        // Act
        CamionDTO resultado = camionService.listarId(id);

        // Assert
        assertNotNull(resultado);

    }

    @Test
    void listarId_camionNoExistente() {
        // Arrange
        Long id = 1L;
        when(camionRepositorio.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> camionService.listarId(id));
        verify(camionRepositorio, times(1)).findById(id);
    }

    @Test
    void add_nuevoCamionSinConductorAsignado_deberiaGuardarYRetornarCamionDTO() {
        // Arrange
        CamionDTO camionDTO = new CamionDTO();
        Camion camionEntity = new Camion();
        Camion camionGuardado = new Camion();
        camionGuardado.setId(1L);
        CamionDTO camionDTOGuardado = new CamionDTO();
        camionDTOGuardado.setId(1L);

        when(camionMapper.convertirACamion(camionDTO)).thenReturn(camionEntity);
        when(camionRepositorio.save(camionEntity)).thenReturn(camionGuardado);
        when(camionMapper.convertirACamionDTO(camionGuardado)).thenReturn(camionDTOGuardado);

        // Act
        CamionDTO resultado = camionService.add(camionDTO);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(camionRepositorio, times(1)).save(camionEntity);
        verify(camionMapper, times(1)).convertirACamion(camionDTO);
        verify(camionMapper, times(1)).convertirACamionDTO(camionGuardado);
    }



    

    

    @Test
    void delete_camionExistente_deberiaEliminarYRetornarCamionDTO() {
        // Arrange
        Long id = 1L;
        Camion camion = new Camion();
        CamionDTO camionDTO = new CamionDTO();
        when(camionRepositorio.findById(id)).thenReturn(Optional.of(camion));
        when(camionMapper.convertirACamionDTO(camion)).thenReturn(camionDTO);

        // Act
        CamionDTO resultado = camionService.delete(id);

        // Assert
        assertNotNull(resultado);
        verify(camionRepositorio, times(1)).findById(id);
        verify(camionRepositorio, times(1)).delete(camion);
        verify(camionMapper, times(1)).convertirACamionDTO(camion);
    }

    @Test
    void getCamionesByFechaCompraRange_deberiaRetornarListaDeCamionDTOEnRango() {
        // Arrange
        Date fromDate = new Date(2024, 0, 1); // Ejemplo de fecha
        Date toDate = new Date(2024, 1, 31); // Ejemplo de fecha
        List<Camion> camiones = Arrays.asList(new Camion(), new Camion());
        List<CamionDTO> camionDTOS = Arrays.asList(new CamionDTO(), new CamionDTO());
        when(camionRepositorio.findByFechaCompraBetween(fromDate, toDate)).thenReturn(camiones);
        when(camionMapper.listEntityToDto(camiones)).thenReturn(camionDTOS);

        // Act
        List<CamionDTO> resultado = camionService.getCamionesByFechaCompraRange(fromDate, toDate);

        // Assert
        assertEquals(2, resultado.size());
        verify(camionRepositorio, times(1)).findByFechaCompraBetween(fromDate, toDate);
        verify(camionMapper, times(1)).listEntityToDto(camiones);
    }

    

    @Test
    void findByFechaCompraBefore_deberiaRetornarListaDeCamionDTOAnterioresAFecha() {
        // Arrange
        Date hasta = new Date(2024, 1, 31); // Ejemplo de fecha
        List<Camion> camiones = Arrays.asList(new Camion(), new Camion());
        List<CamionDTO> camionDTOS = Arrays.asList(new CamionDTO(), new CamionDTO());
        when(camionRepositorio.findByFechaCompraLessThanEqual(hasta)).thenReturn(camiones);
        when(camionMapper.listEntityToDto(camiones)).thenReturn(camionDTOS);

        // Act
        List<CamionDTO> resultado = camionService.findByFechaCompraBefore(hasta);

        // Assert
        assertEquals(2, resultado.size());
        verify(camionRepositorio, times(1)).findByFechaCompraLessThanEqual(hasta);
        verify(camionMapper, times(1)).listEntityToDto(camiones);
    }

    @Test
    void necesitaITV_vehiculoConMenosDe4Anos_deberiaRetornarFalse() {
        // Arrange
        LocalDate fechaMatriculacion = LocalDate.now().minusYears(3);
        LocalDate fechaUltimaITV = LocalDate.now().minusYears(1);

        // Act
        boolean necesita = CamionServiceImpl.necesitaITV(fechaMatriculacion, fechaUltimaITV);

        // Assert
        assertFalse(necesita);
    }

    @Test
    void necesitaITV_vehiculoEntre4Y10Anos_ITVHaceMenosDe2Anos_deberiaRetornarFalse() {
        // Arrange
        LocalDate fechaMatriculacion = LocalDate.now().minusYears(6);
        LocalDate fechaUltimaITV = LocalDate.now().minusYears(1);

        // Act
        boolean necesita = CamionServiceImpl.necesitaITV(fechaMatriculacion, fechaUltimaITV);

        // Assert
        assertFalse(necesita);
    }

    @Test
    void necesitaITV_vehiculoEntre4Y10Anos_ITVHaceMasDe2Anos_deberiaRetornarTrue() {
        // Arrange
        LocalDate fechaMatriculacion = LocalDate.now().minusYears(7);
        LocalDate fechaUltimaITV = LocalDate.now().minusYears(3);

        // Act
        boolean necesita = CamionServiceImpl.necesitaITV(fechaMatriculacion, fechaUltimaITV);

        // Assert
        assertTrue(necesita);
    }

    @Test
    void necesitaITV_vehiculoConMasDe10Anos_ITVHaceMenosDe1Ano_deberiaRetornarFalse() {
        // Arrange
        LocalDate fechaMatriculacion = LocalDate.now().minusYears(12);
        LocalDate fechaUltimaITV = LocalDate.now().minusMonths(6);

        // Act
        boolean necesita = CamionServiceImpl.necesitaITV(fechaMatriculacion, fechaUltimaITV);

        // Assert
        assertFalse(necesita);
    }

    @Test
    void necesitaITV_vehiculoConMasDe10Anos_ITVHaceMasDe1Ano_deberiaRetornarTrue() {
        // Arrange
        LocalDate fechaMatriculacion = LocalDate.now().minusYears(15);
        LocalDate fechaUltimaITV = LocalDate.now().minusYears(2);

        // Act
        boolean necesita = CamionServiceImpl.necesitaITV(fechaMatriculacion, fechaUltimaITV);

        // Assert
        assertTrue(necesita);
    }
}
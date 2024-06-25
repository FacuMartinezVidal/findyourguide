package com.findyourguide.api.service;

import com.findyourguide.api.service.interfaces.IBuyTour;

import lombok.RequiredArgsConstructor;

import com.findyourguide.api.Strategis.Factory.StateFactory;
import com.findyourguide.api.dto.buyservice.BuyTourDTO;
import com.findyourguide.api.dto.buyservice.InputChangeStatus;
import com.findyourguide.api.dto.buyservice.PurchaseUserDTO;
import com.findyourguide.api.dto.service.UpdateServiceDTO;
import com.findyourguide.api.entity.Tourist;
import com.findyourguide.api.entity.User;
import com.findyourguide.api.entity.PurchasedServiceEntitys.PurchasedService;
import com.findyourguide.api.entity.Service.Service;
import com.findyourguide.api.error.ServiceNotFoundException;
import com.findyourguide.api.error.UserNotFoundException;
import com.findyourguide.api.mapper.BuyTourMapper;
import com.findyourguide.api.repository.BuyTourRepository;
import com.findyourguide.api.repository.ServiceRepository;
import com.findyourguide.api.repository.TouristRepository;
import com.findyourguide.api.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class BuyTourImpl implements IBuyTour {

        private final BuyTourRepository buyTourRepository;
        private final ServiceRepository serviceRepository;
        private final TouristRepository touristRepository;
        private final UserRepository userRepository;
        private final StateFactory stateFactory;

        @Override
        public List<BuyTourDTO> findAll() {
                return buyTourRepository.findAll()
                                .stream().map(
                                                BuyTourMapper::mapToBuyTourDTO)
                                .collect(Collectors.toList());
        }

        @Override
        public List<PurchaseUserDTO> findAllByTourist(Long id) throws UserNotFoundException {
                return touristRepository.findById(id)
                                .map(tourist -> tourist.getPurchasedService().stream()
                                                .map(BuyTourMapper::mapToPurchaseUserDTO)
                                                .collect(Collectors.toList()))
                                .orElseThrow(() -> new UserNotFoundException());
        }

        @Override
        public List<PurchaseUserDTO> findAllByGuide(Long guideId)
                        throws UserNotFoundException, ServiceNotFoundException {
                return serviceRepository.findByGuideId(guideId)
                                .stream()
                                .flatMap(service -> buyTourRepository.findByServiceId(service.getId()).stream())
                                .map(BuyTourMapper::mapToPurchaseUserDTO)
                                .collect(Collectors.toList());
        }

        @Override
        public BuyTourDTO findById(Long id) throws ServiceNotFoundException {
                return buyTourRepository.findById(id)
                                .map(BuyTourMapper::mapToBuyTourDTO)
                                .orElseThrow(() -> new ServiceNotFoundException());
        }

        @Override
        public BuyTourDTO create(Long serviceID) throws UserNotFoundException {
                Tourist tourist = touristRepository
                                .findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                                .orElseThrow(() -> new UserNotFoundException());
                Service service = serviceRepository
                                .findById(serviceID).orElseThrow(() -> new ServiceNotFoundException());

                PurchasedService purchasedService = BuyTourMapper.mapToEntityFromCreateService(tourist, service);
                buyTourRepository.save(purchasedService);

                tourist.setBalance(tourist.getBalance() - purchasedService.getBalancePaid());

                userRepository.save(tourist);

                return BuyTourMapper.mapToBuyTourDTO(purchasedService);
        }

        @Override
        public BuyTourDTO changeStatus(InputChangeStatus inputChangeStatus) throws Exception {
                User user = userRepository
                                .findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                                .orElseThrow(UserNotFoundException::new);
                PurchasedService purchaseService = buyTourRepository
                                .findById(inputChangeStatus.getId())
                                .orElseThrow(() -> new ServiceNotFoundException());
                purchaseService.setState(stateFactory.getState(purchaseService.getStatus()));

                purchaseService = purchaseService.nextState(inputChangeStatus.getStatus(), user);

                PurchasedService purchaseServiceSAVED = buyTourRepository.save(purchaseService);
                BuyTourDTO purchaseService2 = buyTourRepository
                                .findById(purchaseServiceSAVED.getId()).map(BuyTourMapper::mapToBuyTourDTO)
                                .orElseThrow(() -> new ServiceNotFoundException());
                return purchaseService2;

        }

        @Override
        public BuyTourDTO update(Long id, UpdateServiceDTO updateServiceDTO) throws ServiceNotFoundException {
                // ! No corresponde actualizar la compra, la cancelacion se maneja en el delete
                // ! y el cambio de estado se crea en una funcion aparte
                return null;
        }

        @Override
        public void deleteById(Long id) throws ServiceNotFoundException {
                if (!buyTourRepository.existsById(id)) {
                        throw new ServiceNotFoundException();
                }
                buyTourRepository.deleteById(id);
        }
}

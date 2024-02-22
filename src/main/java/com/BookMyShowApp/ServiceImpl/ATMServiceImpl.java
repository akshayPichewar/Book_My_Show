package com.BookMyShowApp.ServiceImpl;

import com.BookMyShowApp.dto.ATMDto;
import com.BookMyShowApp.entity.ATM;
import com.BookMyShowApp.repository.ATMRepository;
import com.BookMyShowApp.service.ATMService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ATMServiceImpl implements ATMService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ATMRepository atmRepository;
    @Override
    public ATMDto addATM(ATMDto atmDto)
    {
        ATM atm = modelMapper.map(atmDto,ATM.class);
        ATM save = atmRepository.save(atm);
        return modelMapper.map(atm,ATMDto.class);
    }

}

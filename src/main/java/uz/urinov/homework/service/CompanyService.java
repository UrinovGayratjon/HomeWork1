package uz.urinov.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import uz.urinov.homework.dto.request.RequestCompanyDto;
import uz.urinov.homework.dto.response.ApiResponse;
import uz.urinov.homework.entity.Address;
import uz.urinov.homework.entity.Company;
import uz.urinov.homework.repository.AddressRepository;
import uz.urinov.homework.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AddressRepository addressRepository;

    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    public Company getCompany(Integer companyId) {

        return companyRepository.findById(companyId).orElse(null);
    }

    public ApiResponse saveCompany(RequestCompanyDto newCompanyDto) {
        boolean existByName = companyRepository.existsByCorpName(newCompanyDto.getCorpName());
        if (existByName) {
            return ApiResponse.builder().isSuccess(false).message("Bunday Kompaniya mavjud").build();
        } else {

            Address address;
            Optional<Address> optionalAddress = addressRepository.getByStreetAndHomeNumber(newCompanyDto.getStreet(), newCompanyDto.getHomeNumber());

            address = optionalAddress.orElseGet(() ->
                    addressRepository.save(
                            Address.builder().homeNumber(newCompanyDto.getHomeNumber()).street(newCompanyDto.getStreet()).build()
                    )
            );

            Company company = new Company();
            company.setAddress(address);
            company.setCorpName(newCompanyDto.getCorpName());
            company.setDirectorName(newCompanyDto.getDirectorName());
            companyRepository.save(company);
            return ApiResponse.builder().isSuccess(true).message("Success").build();
        }
    }

    public ApiResponse editCompany(Integer companyId, RequestCompanyDto newCompanyDto) {

        Optional<Company> optionalCompany = companyRepository.findById(companyId);

        if (optionalCompany.isEmpty()) {
            return ApiResponse.builder().isSuccess(false).message("Bunday Kompaniya mavjud emas").build();
        }

        boolean existsByCorpName = companyRepository.existsByCorpName(newCompanyDto.getCorpName());

        if (existsByCorpName) {
            return ApiResponse.builder().message("Bunday nomdagi kompaniya mavjud!").isSuccess(false).build();
        }

        Company company = optionalCompany.get();
        company.setDirectorName(newCompanyDto.getDirectorName());
        company.setCorpName(newCompanyDto.getCorpName());
        Address address;
        Optional<Address> optionalAddress = addressRepository.getByStreetAndHomeNumber(newCompanyDto.getStreet(), newCompanyDto.getHomeNumber());

        address = optionalAddress.orElseGet(() ->
                addressRepository.save(
                        Address.builder().homeNumber(newCompanyDto.getHomeNumber()).street(newCompanyDto.getStreet()).build()
                )
        );

        company.setAddress(address);

        companyRepository.save(company);
        return ApiResponse.builder().isSuccess(true).message("Kompaniya tahrirlandi").build();
    }
}

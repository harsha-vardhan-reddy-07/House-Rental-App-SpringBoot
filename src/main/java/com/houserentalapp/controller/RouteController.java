package com.houserentalapp.controller;

import com.houserentalapp.models.ApplicationModel;
import com.houserentalapp.models.PropertiesModel;
import com.houserentalapp.models.UserModel;
import com.houserentalapp.repos.ApplicationsRepo;
import com.houserentalapp.repos.PropertiesRepo;
import com.houserentalapp.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Controller
public class RouteController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PropertiesRepo propertiesRepo;

    @Autowired
    ApplicationsRepo applicationsRepo;


    @RequestMapping("/")
    public ModelAndView landingPage(){
        return new ModelAndView("landing");
    }

    @RequestMapping("/login")
    public ModelAndView loginPage(){
        return new ModelAndView("login");
    }

    @PostMapping("/login-user")
    public UserModel loginUser(@ModelAttribute UserModel userData, Model model) {
        try {
            UserModel user = userRepo.findByEmail(userData.getEmail());

            if (user.getPassword().equals(userData.getPassword())) {
                // Return userData as JSON response
                return user;
            }
            return null;
        } catch (Exception e) {
            // Handle any exceptions and return an error response
            return null;
        }
    }

    @RequestMapping("/register")
    public ModelAndView registerPage(){
        return new ModelAndView("register");
    }

    @RequestMapping("/register-user")
    public UserModel registerUser(@ModelAttribute UserModel userData, Model model) {
        try {

            UserModel user = userRepo.save(userData);
            return user;

        } catch (Exception e) {

            return null;
        }
    }



    @RequestMapping("/user/{id}")
    public ModelAndView userHomePage(@PathVariable("id") String id){

        List<PropertiesModel> propertiesList = propertiesRepo.findAll();
        System.out.println(propertiesList);

        ModelMap modelMap = new ModelMap();

        modelMap.addAttribute("userId", id);
        modelMap.addAttribute("propertiesList", propertiesList);


        return new ModelAndView("user/home", modelMap);
    }


    @RequestMapping("/book-property")
    public ModelAndView bookUserProperty(@ModelAttribute ApplicationModel applicationData, Model model){

        System.out.println(applicationData);
        Optional<UserModel> user = userRepo.findById(applicationData.getApplicantId());

        Optional<PropertiesModel> property = propertiesRepo.findById(applicationData.getPropertyId());

        System.out.println(property);

        System.out.println(user);

        if(user.isPresent() && property.isPresent()){
            UserModel userData = user.get();

            PropertiesModel propertyData = property.get();


            applicationData.setOwnerId(propertyData.getOwnerId());
            applicationData.setOwnerName(propertyData.getOwnerName());
            applicationData.setCarouselImage1(propertyData.getCarouselImage1());
            applicationData.setCarouselImage2(propertyData.getCarouselImage2());
            applicationData.setCarouselImage3(propertyData.getCarouselImage3());
            applicationData.setDescription(propertyData.getDescription());
            applicationData.setRent(propertyData.getRent());
            applicationData.setDeposit(propertyData.getDeposit());
            applicationData.setAgreementDuration(propertyData.getAgreementDuration());
            applicationData.setAddress(propertyData.getAddress());
            applicationData.setApplicantName(userData.getUsername());
            applicationData.setApplicantEmail(userData.getEmail());
            applicationData.setStatus("Pending");


            List<String> applicantsList = propertyData.getApplicantsList();

            applicantsList.add(userData.get_id());

            propertyData.setApplicantsList(applicantsList);


            applicationsRepo.save(applicationData);
            propertiesRepo.save(propertyData);

        }

        String path = "redirect:/user/" + user.get().get_id();

        return new ModelAndView(path);
    }



    @RequestMapping("/user-rentals/{id}")
    public ModelAndView userRentalsPage(@PathVariable("id") String id){

        List<PropertiesModel> propertiesList = propertiesRepo.findAll();

        List<PropertiesModel> filteredPropertiesList = propertiesList.stream()
                .filter(property -> id.equals(property.getTenantId()))
                .collect(Collectors.toList());

        System.out.println(filteredPropertiesList);


        return new ModelAndView("user/rentals", "propertiesList", filteredPropertiesList);

    }

    @RequestMapping("/vacate-property")
    public ModelAndView vacateProperty(@ModelAttribute PropertiesModel propertyInfo, Model model){

        Optional<PropertiesModel> property = propertiesRepo.findById(propertyInfo.getOwnerId());

        System.out.println(property);


        if(property.isPresent()){

            PropertiesModel propertyData = property.get();

            propertyData.setStatus("Available");
            propertyData.setTenantId("");
            propertyData.setTenantName("");
            propertyData.setRentStartDate("");

            propertiesRepo.save(propertyData);
        }

        String path = "redirect:/user-applications/" + propertyInfo.getTenantId();


        return new ModelAndView(path);
    }


    @RequestMapping("/user-applications/{id}")
    public ModelAndView userApplicationsPage(@PathVariable("id") String id){

        List<ApplicationModel> applicationsList = applicationsRepo.findAll();

        List<ApplicationModel> filteredApplicationsList = applicationsList.stream()
                .filter(application -> id.equals(application.getApplicantId()))
                .collect(Collectors.toList());

        System.out.println(filteredApplicationsList);

        return new ModelAndView("user/applications", "applicationsList", filteredApplicationsList);
    }

    @RequestMapping("/withdraw-application")
    public ModelAndView withdrawApplicationProperty(@ModelAttribute ApplicationModel applicationData, Model model){

        Optional<ApplicationModel> application = applicationsRepo.findById(applicationData.getPropertyId());

        Optional<PropertiesModel> property = propertiesRepo.findById(application.get().getPropertyId());

        System.out.println(property);


        if(property.isPresent()){

            PropertiesModel propertyData = property.get();

            List<String> applicantsList = propertyData.getApplicantsList();

            applicantsList.remove(application.get().getApplicantId());

            propertyData.setApplicantsList(applicantsList);

            propertiesRepo.save(propertyData);
        }

        String path = "redirect:/user-applications/" + application.get().getApplicantId();

        applicationsRepo.deleteById(application.get().get_id());

        return new ModelAndView(path);
    }


    @RequestMapping("/owner/{id}")
    public ModelAndView ownerHomePage(@PathVariable("id") String id){

        List<PropertiesModel> propertiesList = propertiesRepo.findAll();
        System.out.println(propertiesList);

        List<PropertiesModel> filteredPropertiesList = propertiesList.stream()
                .filter(property -> id.equals(property.getOwnerId()))
                .collect(Collectors.toList());

        return new ModelAndView("owner/home", "propertiesList", filteredPropertiesList);
    }



    @RequestMapping("/owner-applications/{id}")
    public ModelAndView ownerApplicationsPage(@PathVariable("id") String id){

        List<ApplicationModel> applicationsList = applicationsRepo.findAll();
        System.out.println(applicationsList);

        List<ApplicationModel> filteredApplicationsList = applicationsList.stream()
                .filter(application -> id.equals(application.getOwnerId()))
                .collect(Collectors.toList());

        return new ModelAndView("owner/applications", "applicationsList", filteredApplicationsList);
    }

    @RequestMapping("/approve-application")
    public ModelAndView approveApplication(@ModelAttribute ApplicationModel applicationData, Model model){

        Optional<ApplicationModel> application = applicationsRepo.findById(applicationData.getOwnerId());

        Optional<PropertiesModel> property = propertiesRepo.findById(application.get().getPropertyId());

        System.out.println(property);


        if(property.isPresent() && application.isPresent()){

            ApplicationModel applicationInfo = application.get();

            PropertiesModel propertyData = property.get();

            List<String> applicantsList = propertyData.getApplicantsList();

            applicantsList.remove(applicationInfo.getApplicantId());

            propertyData.setApplicantsList(applicantsList);

            applicationInfo.setStatus("Accepted");

            propertyData.setTenantId(applicationInfo.getApplicantId());
            propertyData.setTenantName(applicationInfo.getApplicantName());
            propertyData.setStatus("Booked");

            LocalDate currentDate = LocalDate.now();
            String currentDateAsString = currentDate.toString();

            propertyData.setRentStartDate(currentDateAsString);


            applicationsRepo.save(applicationInfo);
            propertiesRepo.save(propertyData);
        }

        String path = "redirect:/user-applications/" + application.get().getApplicantId();

        return new ModelAndView(path);
    }

    @RequestMapping("/reject-application")
    public ModelAndView rejectApplication(@ModelAttribute ApplicationModel applicationData, Model model){

        Optional<ApplicationModel> application = applicationsRepo.findById(applicationData.getOwnerId());

        Optional<PropertiesModel> property = propertiesRepo.findById(application.get().getPropertyId());

        System.out.println(property);


        if(property.isPresent() && application.isPresent()){

            ApplicationModel applicationInfo = application.get();

            PropertiesModel propertyData = property.get();

            List<String> applicantsList = propertyData.getApplicantsList();

            applicantsList.remove(applicationInfo.getApplicantId());

            propertyData.setApplicantsList(applicantsList);

            applicationInfo.setStatus("Rejected");


            applicationsRepo.save(applicationInfo);
            propertiesRepo.save(propertyData);
        }

        String path = "redirect:/user-applications/" + application.get().getApplicantId();

        return new ModelAndView(path);
    }


    @RequestMapping("/new-property/{id}")
    public ModelAndView ownerNewPropertyPage(){


        return new ModelAndView("owner/newProperty");
    }

    @RequestMapping("/add-property")
    public PropertiesModel addNewProperty(@ModelAttribute PropertiesModel propertyData, Model model){

        propertyData.setStatus("Available");

        propertyData.setTenantId("");
        propertyData.setTenantName("");
        propertyData.setRentStartDate("");

        List<String> applicList = new ArrayList<>();

        propertyData.setApplicantsList(applicList);

        System.out.println(propertyData);

        PropertiesModel property = propertiesRepo.save(propertyData);

        return property;
    }

    @RequestMapping("/update-property")
    public ModelAndView updatePage(@ModelAttribute PropertiesModel propertyData, Model model){

        Optional<PropertiesModel> property = propertiesRepo.findById(propertyData.getOwnerId());

        PropertiesModel propertyInfo = property.get();

        return new ModelAndView("owner/updateProperty", "propertyData", propertyInfo);
    }


    @RequestMapping("/update-data")
    public ModelAndView updatePropertyData(@ModelAttribute PropertiesModel propertyData, Model model){

        Optional<PropertiesModel> property = propertiesRepo.findById(propertyData.getOwnerId());

        PropertiesModel propertyInfo = property.get();

        propertyInfo.setDescription(propertyData.getDescription());
        propertyInfo.setArea(propertyData.getArea());
        propertyInfo.setCarouselImage1(propertyData.getCarouselImage1());
        propertyInfo.setCarouselImage2(propertyData.getCarouselImage2());
        propertyInfo.setCarouselImage3(propertyData.getCarouselImage3());
        propertyInfo.setAddress(propertyData.getAddress());
        propertyInfo.setRent(propertyData.getRent());
        propertyInfo.setDeposit(propertyData.getDeposit());
        propertyInfo.setFurnished(propertyData.getFurnished());
        propertyInfo.setAvailableFor(propertyData.getAvailableFor());
        propertyInfo.setAgreementDuration(propertyData.getAgreementDuration());

        propertiesRepo.save(propertyInfo);

        String path = "redirect:/owner/" + propertyData.getTenantId();

        return new ModelAndView(path);
    }



    @RequestMapping("/admin")
    public ModelAndView adminHomePage(){

        List<PropertiesModel> propertiesList = propertiesRepo.findAll();

        return new ModelAndView("admin/home", "propertiesList", propertiesList);
    }
    @RequestMapping("/all-applications")
    public ModelAndView allApplicationsPage(){

        List<ApplicationModel> applicationsList = applicationsRepo.findAll();
        return new ModelAndView("admin/applications", "applicationsList", applicationsList);
    }
    @RequestMapping("/all-users")
    public ModelAndView allUsersPage(){
        List<UserModel> usersList = userRepo.findAll();

        List<UserModel> filteredUsersList = usersList.stream()
                .filter(user -> user.getUsertype().equals("customer") || user.getUsertype().equals("owner"))
                .collect(Collectors.toList());


        return new ModelAndView("admin/allUsers", "usersList", filteredUsersList);
    }

}

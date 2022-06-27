package tw.team5.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tw.team5.bean.Company;
import tw.team5.service.CompanyService;

@RequestMapping("/company")
@Controller
public class CompanyServlet {
	
	@Autowired
	private CompanyService companyService;
	
	@GetMapping("/list")
	public String listCompanies(Model m) {
		List<Company> companies = companyService.getAllCompanies();
		m.addAttribute("companies",companies);
		return "CompanyDashBoard";
	}
	
	@GetMapping("/showForm")
    public String showFormForAdd(Model m) {
		Company company = new Company();
        m.addAttribute("company", company);
        return "CompanyCreate";
    }
	
	@PostMapping("/saveCompany")
	public String saveCustomer(@ModelAttribute("company") Company company ,Model m) {
		
		//設定輸入錯誤
		Map<String, String> errorMsg = new HashMap<String, String>();
		m.addAttribute("error", errorMsg);
		//讀取資料
		int compid=company.getCompid();
		String compwd = company.getCompwd();
		String corpname = company.getCorpname();
		String industry = company.getIndustry();
		String contact = company.getContact();
		String compaddress = company.getCompaddress();
		String capital = company.getCapital();
		
		
		//判斷新增是否錯誤
		if(companyService.isDup(compid)) {
			errorMsg.put("compid", "帳號(統編)重複，請重新輸入新帳號");
		}
		if(compwd== null || compwd.trim().length()==0){
			errorMsg.put("compwd","密碼不可為空");
		}
		if(corpname== null || corpname.trim().length()==0){
			errorMsg.put("corpname","公司名稱不可為空");
		}
		if(industry== null || industry.trim().length()==0){
			errorMsg.put("industry","產業類別不可為空");
		}
		if(contact== null || contact.trim().length()==0){
			errorMsg.put("contact","聯絡人不可為空");
		}
		if(compaddress== null || compaddress.trim().length()==0){
			errorMsg.put("compaddress","公司地址不可為空");
		}
		if(capital== null || capital.trim().length()==0){
			errorMsg.put("capital","資本額不可為空");
		}
		
		if(!errorMsg.isEmpty()){
			return "CompanyCreate";
		}
		
		
		companyService.save(company);
		return "redirect:/company/list";
	}
	
	@GetMapping("/detail")
	public String processShowDetail(@RequestParam("companyId") int detailId,Model m){
		Company companydeatail = companyService.getCompany(detailId);
		m.addAttribute("companydeatail",companydeatail);
		return "CompanyDetail";
	}
	@GetMapping("/showupdateinformation")
	public String showInformaionFromUpdate(@RequestParam("companyId") Integer updateId,Model m){
		Company companyupdate = companyService.getCompany(updateId);
		m.addAttribute("companyupdate",companyupdate);
		return "CompanyUpdate";
	}
	@PostMapping("/update")
	public String processUpdate(Company company){
		companyService.updateCompany(company);
		return "redirect:/company/list";
	}
	@GetMapping("/delete")
	public String processDelete(@RequestParam("companyId") int deleteId){
		companyService.deleteCompany(deleteId);
		return "redirect:/company/list";
	}
	
}

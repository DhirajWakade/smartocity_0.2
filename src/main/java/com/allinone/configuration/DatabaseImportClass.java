package com.allinone.configuration;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.allinone.model.business.BusinessCategory;
import com.allinone.model.business.BusinessTypes;
import com.allinone.model.product.ProductSizeType;
import com.allinone.model.product.ProductTypes;
import com.allinone.repository.business.BusinessCategoryRepository;
import com.allinone.repository.business.BusinessTypesRepository;
import com.allinone.repository.product.ProductSizeTypeRepository;
import com.allinone.repository.product.ProductTypesRepository;
import com.allinone.services.BusinessDetailsService;


@Service
public class DatabaseImportClass 
{
	@Autowired
    ResourceLoader resourceLoader;
	
	@Autowired
	private ProductSizeTypeRepository  productSizeTypeRepository;
	
	@Autowired
	private BusinessTypesRepository businessTypesRepository;
	
	@Autowired
	private ProductTypesRepository ProductTypesRepository;
	@Autowired
	private BusinessCategoryRepository businessCategoryRepository;
	
	@Autowired
	private BusinessDetailsService businessDetailsService;
	
	private final Logger log =LoggerFactory.getLogger(this.getClass());
	
	public Boolean readExcelFile(File exelFile,String tablenames) throws InvalidFormatException, IllegalStateException, IOException	
	{
		 
        Workbook workbook = new XSSFWorkbook(exelFile);
        
        Sheet firstSheet = workbook.getSheetAt(0);
        String className=firstSheet.getSheetName();
        Iterator<Row> rowIterator = firstSheet.iterator();
        //Row columgName=firstSheet.getRow(0);  
        Boolean isFirstRow=true;       
        
        while (rowIterator.hasNext()) {     
        	
            Row nextRow = rowIterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();            
            
            while (cellIterator.hasNext()) {
            	Cell nextCell = cellIterator.next();

                int columnIndex = nextCell.getColumnIndex();

                switch (columnIndex) {
                case 0:
                    String name = nextCell.getStringCellValue(); 
                    System.out.print("Size ="+name+" , ");
                    break;
                case 1:
                    String enrollDate = nextCell.getStringCellValue();
                    System.out.println("Size Desc="+enrollDate);
                    break;                                   
                }
            }
            
            if(isFirstRow)
        	{
        		isFirstRow=false;
        	}     
            		

        }
		
		return true;
		
	}
	 public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException 
	 {
	     File convFile = new File( multipart.getOriginalFilename());
	    
	     return convFile;
	 }
	 
	 public String read(LinkedList<String> mapTable) throws IOException, InvalidFormatException, IllegalStateException
	 {
		 Resource resource = resourceLoader.getResource("classpath:database.xlsx");

		    InputStream input = resource.getInputStream();

		    File file = resource.getFile();
		    readFile(file,mapTable);
		    
		return file.getName();
	 }
	 
	 public Boolean storeProductSizeType(Sheet productSizeTypeSheet)
	 {
		 log.info("#### ProductSizeType ####");
		 Iterator<Row> rowIterator = productSizeTypeSheet.iterator();
		 Boolean isFirstRow=true;
		 while (rowIterator.hasNext()) {
	        	
	            Row nextRow = rowIterator.next();
	            Iterator<Cell> cellIterator = nextRow.cellIterator();           
	            ProductSizeType p=new ProductSizeType();
	            if(!isFirstRow)
	        	{
	            	BusinessTypes businessType=null;
	            	while (cellIterator.hasNext()) {
		            	Cell nextCell = cellIterator.next();
		                int columnIndex = nextCell.getColumnIndex();
		                switch (columnIndex) {
		                case 0:
		                		int id=(int)nextCell.getNumericCellValue();
			                    p.setSizeTypeId(new Long(id));
			                    break;
		                case 1:
		                	String sizeType="";
		                	if(nextCell.getCellType() == CellType.NUMERIC) {
		                		sizeType = NumberToTextConverter.toText(nextCell.getNumericCellValue());
		                	}
		                	else
		                	{
		                		sizeType=nextCell.getStringCellValue(); 
		                	}
		                    p.setSizeTypeName(sizeType);
		                    break;
		                case 2:
		                	String sizeTypeDesc="";
		                	if(nextCell.getCellType() == CellType.NUMERIC) {
		                		sizeTypeDesc = NumberToTextConverter.toText(nextCell.getNumericCellValue());
		                	}
		                	else
		                	{
		                		sizeTypeDesc=nextCell.getStringCellValue(); 
		                	}
		                    p.setSizeTypeDesc(sizeTypeDesc);
		                    break;
		                /*case 3:
		                		int businessTypeId=(int) nextCell.getNumericCellValue();
		                		log.info("### businessdfgType="+businessTypeId);
		                		businessType=businessTypesRepository.findById(new Long(businessTypeId)).get();
		                    break;   */ 
		                }
		            }
	            	log.info("### sizeTypeName="+p.getSizeTypeName()+" , SizeTypeDesc="+p.getSizeTypeDesc());
	            	log.info("### businessType="+businessType);
	            	//p.addBusinessTypes(businessType);
	            	if(p.getSizeTypeId()!=null)
	            	{
	            	ProductSizeType pstype=productSizeTypeRepository.save(p);
	            	}
	            	//businessType.addProductSizeType(pstype);
	        		//businessTypesRepository.save(businessType);
	        		
	        	}   
	            isFirstRow=false;

	        }
		 
		 return true;
	 }
	 
	 public Boolean storBusinessType(Sheet productSizeTypeSheet)
	 {
		 log.info("#### Business Types ####");
		 Iterator<Row> rowIterator = productSizeTypeSheet.iterator();
		 Boolean isFirstRow=true;
		 while (rowIterator.hasNext()) {
	        	
	            Row nextRow = rowIterator.next();
	            Iterator<Cell> cellIterator = nextRow.cellIterator();           
	            BusinessTypes b=new BusinessTypes();
	            if(!isFirstRow)
	        	{
	            	while (cellIterator.hasNext()) {
		            	Cell nextCell = cellIterator.next();
		                int columnIndex = nextCell.getColumnIndex();
		                switch (columnIndex) {
		                case 0:
		                	int id = (int) nextCell.getNumericCellValue(); 
		                    b.setBusinessTypeId(new Long(id));
		                    break;
		                case 1:
		                    String bsTypeName = nextCell.getStringCellValue(); 
		                    b.setBusinessTypeName(bsTypeName);
		                    b.setBusinessTypeCode(bsTypeName.toUpperCase());
		                    break;
		                case 2:
		                	int isGstRequired = (int) nextCell.getNumericCellValue(); 
		                    b.setIsGstNoRequired(isGstRequired==1?true:false);
		                    break;
		                case 3:
		                	String isMultiselection =nextCell.getStringCellValue(); 
		                    b.setIsMultiSelection(isMultiselection);
		                    break;
		                case 4:
		                	String isSearchProduct =nextCell.getStringCellValue(); 
		                    b.setIsProductSearchAllow(isSearchProduct);
		                    break;    
		                }
		            }
	            	log.info("### Name="+b.getBusinessTypeName()+" , GstRequired="+b.getIsGstNoRequired()+" ,isMultiSelection="+b.getIsMultiSelection());
	            	if(b.getBusinessTypeId()!=null) {
	            	businessTypesRepository.save(b);
	            	}
	        		
	        	}   
	            isFirstRow=false;

	        }
		 
		 return true;
	 }

	 
	 public Boolean storeProductType(Sheet productSizeTypeSheet)
	 {
		 log.info("#### Product Types ####");
		 Iterator<Row> rowIterator = productSizeTypeSheet.iterator();
		 Boolean isFirstRow=true;
		 while (rowIterator.hasNext()) {
	        	
	            Row nextRow = rowIterator.next();
	            Iterator<Cell> cellIterator = nextRow.cellIterator();           
	            ProductTypes p=new ProductTypes();
	            if(!isFirstRow)
	        	{
	            	while (cellIterator.hasNext()) {
		            	Cell nextCell = cellIterator.next();
		                int columnIndex = nextCell.getColumnIndex();
		                switch (columnIndex) {
		                case 0:
		                	int id = (int) nextCell.getNumericCellValue(); 
		                    p.setProductTypeId(new Long(id));
		                    break;
		                case 1:
		                	String name =nextCell.getStringCellValue(); 
		                    p.setProductType(name);
		                    break;
		                	
		                case 2:
		                	int btid = (int) nextCell.getNumericCellValue(); 
		                    p.setBusinessTypes(businessTypesRepository.findById(new Long(btid)).get());
		                    break;     
		                }
		            }
	            	
	            	if(p.getProductTypeId()!=null)
	            	{
	            		log.info("### Product Id="+p.getProductTypeId()+" , ProductType="+p.getProductType()+" , BusTyName="+p.getBusinessTypes().getBusinessTypeName());
	            		ProductTypesRepository.save(p);
	            	}
	            	
	        	}   
	            isFirstRow=false;

	        }
		 
		 return true;
	 }

	 
	 public Boolean storeBusinessType_ProductSizeType(Sheet productSizeTypeSheet)
	 {
		 log.info("#### Business_Type_Product size Types ####");
		 Iterator<Row> rowIterator = productSizeTypeSheet.iterator();
		 Boolean isFirstRow=true;
		 while (rowIterator.hasNext()) {
	        	
	            Row nextRow = rowIterator.next();
	            Iterator<Cell> cellIterator = nextRow.cellIterator();           
	            if(!isFirstRow)
	        	{
	            	BusinessTypes businessType=null;
	            	String productSizeIds="";
	            	while (cellIterator.hasNext()) {
		            	Cell nextCell = cellIterator.next();
		                int columnIndex = nextCell.getColumnIndex();
		                switch (columnIndex) {
		                case 0:
		                	int businesid = (int) nextCell.getNumericCellValue(); 
		                	businessType=businessTypesRepository.findById(new Long(businesid)).get();
		                    break;
		                case 1:
		                	productSizeIds="";
		                	if(nextCell.getCellType() == CellType.NUMERIC) {
		                		productSizeIds = NumberToTextConverter.toText(nextCell.getNumericCellValue());
		                	}
		                	else
		                	{
		                		productSizeIds=nextCell.getStringCellValue(); 
		                	}
		                    break;
		                }
		            }
	            	if(businessType!=null) {
	            	List<ProductSizeType>productSizeTypeList=new ArrayList<ProductSizeType>();
	            	String[] arr=productSizeIds.split(",");
	            	for(String id:arr)
	            	{
	            		if(!id.equals("")) {
	            		Long ptid=Long.valueOf(id);
	            		productSizeTypeList.add(productSizeTypeRepository.findById(ptid).get());
	            		}
	            	}
	            	businessType.setProductSizeTypes(productSizeTypeList);
	            	BusinessTypes bt=businessTypesRepository.save(businessType);
	            	
	            	for(ProductSizeType pt:productSizeTypeList)
	            	{
	            		pt.addBusinessTypes(bt);
	            		if(pt.getBusinessTypes()!=null)
	            		{
	            		productSizeTypeRepository.save(pt);
	            		}
	            	}
	            	}
	        	}   
	            isFirstRow=false;

	        }
		 
		 return true;
	 }
	 public Boolean storBusinessCat(Sheet businescat)
	 {
		 log.info("#### Business Categories ####");
		 Iterator<Row> rowIterator = businescat.iterator();
		 Boolean isFirstRow=true;
		 while (rowIterator.hasNext()) {
	        	
	            Row nextRow = rowIterator.next();
	            Iterator<Cell> cellIterator = nextRow.cellIterator();           
	            BusinessCategory bc=new BusinessCategory();
	            if(!isFirstRow)
	        	{
	            	while (cellIterator.hasNext()) {
		            	Cell nextCell = cellIterator.next();
		                int columnIndex = nextCell.getColumnIndex();
		                switch (columnIndex) {
		                case 0:
		                	int id = (int) nextCell.getNumericCellValue(); 
		                    bc.setBcId(new Long(id));
		                    break;
		                case 1:
		                	String name =nextCell.getStringCellValue(); 
		                    bc.setBcName(name);
		                    bc.setBcCode(name.toUpperCase());
		                    break;
		                    
		                }
		            }
	            	log.info("### Business cat Id="+bc.getBcId()+" , business cat name="+bc.getBcName());
	            	businessCategoryRepository.save(bc);
	            	
	        	}   
	            isFirstRow=false;

	        }
		 
		 return true;
	 }

	 public Boolean storeBusinessFieldMaster(Sheet businessFiledMasterShhet)
	 {
		 log.info("#### Business Field Master ####");
		 Iterator<Row> rowIterator = businessFiledMasterShhet.iterator();
		 Boolean isFirstRow=true;
		 Map<Long,String>filedMap=new HashMap<Long, String>();
		 while (rowIterator.hasNext()) {
	        	
	            Row nextRow = rowIterator.next();
	            Iterator<Cell> cellIterator = nextRow.cellIterator();  
	           
	            if(!isFirstRow)
	        	{
	            	 int fid = 0;
	 	            String finame=null;
	            	while (cellIterator.hasNext()) {
		            	Cell nextCell = cellIterator.next();
		                int columnIndex = nextCell.getColumnIndex();
		                switch (columnIndex) {
		                case 0:
		                	fid = (int) nextCell.getNumericCellValue(); 
		                    break;
		                case 1:
		                	 finame=nextCell.getStringCellValue(); 
		                    break;
		                }
		            }

	            	filedMap.put(Long.valueOf(fid), finame);
	            	
	        	}   
	            isFirstRow=false;

	        }
		 
		 if(businessDetailsService.saveBusinessFieldmaster(filedMap)==null)
		 {
			 return false;
		 }
		 else {
			 return true;
		 }
		 
		
	 }
	 
	 public void readFile(File exelFile,LinkedList<String> strTble) throws InvalidFormatException, IOException
	 {
		 Workbook workbook = new XSSFWorkbook(exelFile);
		 Iterator<Sheet> sheetIterator = workbook.iterator();	 
	        
		 List<String>tblNameList=strTble;
		 
			 while (sheetIterator.hasNext()) 
			 { 
				 //for(String tbl:strTble) 
				 //{
		        	Sheet sheet=sheetIterator.next();
		        	if("tbl_business_cat".equalsIgnoreCase(sheet.getSheetName()) && tblNameList.contains("tbl_business_cat"))// && "tbl_business_type".equalsIgnoreCase(tbl))
		        	{
		        		storBusinessCat(sheet);
		        	}
		        	else if("tbl_business_type".equalsIgnoreCase(sheet.getSheetName()) && tblNameList.contains("tbl_business_type"))// && "tbl_business_type".equalsIgnoreCase(tbl))
		        	{
		        		storBusinessType(sheet);
		        	}
		        	else if("tbl_product_type".equalsIgnoreCase(sheet.getSheetName()) && tblNameList.contains("tbl_product_type"))// && "tbl_business_type".equalsIgnoreCase(tbl))
			        {
			        		storeProductType(sheet);
			        }
		        	else if("tbl_product_size_type".equalsIgnoreCase(sheet.getSheetName()) && tblNameList.contains("tbl_product_size_type"))// && "tbl_product_size_type".equalsIgnoreCase(tbl))
		        	{
		        		storeProductSizeType(sheet);
		        	}
		        	else if("business_type_product_size_type".equalsIgnoreCase(sheet.getSheetName()) && tblNameList.contains("business_type_product_size_type"))// && "tbl_business_type".equalsIgnoreCase(tbl))
			        {
		        		storeBusinessType_ProductSizeType(sheet);
			        }
		        	else if("tbl_business_field_mst".equalsIgnoreCase(sheet.getSheetName()) && tblNameList.contains("tbl_business_field_mst"))
		        	{
		        		storeBusinessFieldMaster(sheet);
		        	}
				 //}
		     }
	        
	      workbook.close();
	 }

}

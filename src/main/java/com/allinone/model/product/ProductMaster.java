package com.allinone.model.product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.allinone.repository.product.ProductSubImage;
@Entity
@Table(name="tbl_product_master")
public class ProductMaster 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="prod_id")
	private Long prodId;
	
	@Column(name="product_code")
	private String productCode;
	
	@Column(name="product_title")
	private String productTitle;
	
	@Column(name="product_manu_name")
	private String Manufacturer_Name;
	
	@Column(name="name")
	private String productName;
	
	@Column(name="brand")
	private String productBrand;
	
	@Column(name="size")
	private String productSize;
	
	@OneToOne
	@JoinColumn(name = "product_type_id")
	private ProductTypes productTypes;
	
	@Column(name="vegornon")
	private String vegOrNonVeg;	
	
	
	@OneToOne
	@JoinColumn(name = "size_type_id")
	private ProductSizeType productSizeType;
	
	@Column(name="color")
	private String productColor;
	
	@Column(name="encoluse_material")
	private String productMaterial;
	
	@OneToMany(cascade = CascadeType.ALL)	
	@JoinColumn(name = "prodId")
	private List<IncludedCompoment> includedCompoments;
	
	@OneToMany(cascade = CascadeType.ALL)	
	@JoinColumn(name = "prodId")
	private List<ProductKeyword> productKeywords;
	
	@Column(name="description")
	private String desciption;
	
	@Column(name="img_url")
	private String productImageUrl;
	
	@Column(name="weight")
	private Double productWeight;
	
	
	@Column(name="origin_of_product")
	private String orginOfProduct;
	
	@CreationTimestamp
	@Column(name = "insert_time")
	private LocalDateTime insertTime;
	
	@UpdateTimestamp
	@Column(name = "update_time")
	private LocalDateTime updateTime;
	
	@OneToMany(cascade = CascadeType.ALL)	
	@JoinTable(name = "tbl_product_mt_size_price",
		    joinColumns = @JoinColumn(name = "prod_mt_id"),
		    inverseJoinColumns = @JoinColumn(name = "mspp_id"))
	private List<MultiSizeProductPrice> multiSizeProductPrices;
	
	@Column(name = "isProductReturnable")
	private String isProductReturnable;
	
	@Column(name = "returnInDays")
	private Integer returnInDays;
	
	@OneToMany
	//@JoinTable(name = "tbl_product_sub_img_product_master",
    //joinColumns = @JoinColumn(name = "prod_mt_id"),
    //inverseJoinColumns = @JoinColumn(name = "sub_img_id"))
	private List<ProductSubImage>productSubImages=new ArrayList<ProductSubImage>();

	public void addProductSubImages(ProductSubImage m)
	{
		this.productSubImages.add(m);
	}
	
	
	
	public Double getProductWeight() {
		return productWeight;
	}



	public void setProductWeight(Double productWeight) {
		this.productWeight = productWeight;
	}



	public String getIsProductReturnable() {
		return isProductReturnable;
	}



	public void setIsProductReturnable(String isProductReturnable) {
		this.isProductReturnable = isProductReturnable;
	}



	public Integer getReturnInDays() {
		return returnInDays;
	}



	public void setReturnInDays(Integer returnInDays) {
		this.returnInDays = returnInDays;
	}



	public String getProductTitle() {
		return productTitle;
	}


	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}


	public String getManufacturer_Name() {
		return Manufacturer_Name;
	}


	public void setManufacturer_Name(String manufacturer_Name) {
		Manufacturer_Name = manufacturer_Name;
	}


	public String getVegOrNonVeg() {
		return vegOrNonVeg;
	}


	public void setVegOrNonVeg(String vegOrNonVeg) {
		this.vegOrNonVeg = vegOrNonVeg;
	}


	public List<ProductSubImage> getProductSubImages() {
		return productSubImages;
	}

	public void setProductSubImages(List<ProductSubImage> productSubImages) {
		this.productSubImages = productSubImages;
	}

	public void addMultiSizeProductPrices(MultiSizeProductPrice m)
	{
		this.multiSizeProductPrices.add(m);
	}
	
	public List<MultiSizeProductPrice> getMultiSizeProductPrices() {
		return multiSizeProductPrices;
	}

	public void setMultiSizeProductPrices(List<MultiSizeProductPrice> multiSizeProductPrices) {
		this.multiSizeProductPrices = multiSizeProductPrices;
	}

	public Long getProdId() {
		return prodId;
	}

	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	

	public String getProductSize() {
		return productSize;
	}

	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}

	public ProductTypes getProductTypes() {
		return productTypes;
	}

	public void setProductTypes(ProductTypes productTypes) {
		this.productTypes = productTypes;
	}

	public ProductSizeType getProductSizeType() {
		return productSizeType;
	}

	public void setProductSizeType(ProductSizeType productSizeType) {
		this.productSizeType = productSizeType;
	}

	public String getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String prodcutName) {
		this.productName = prodcutName;
	}

	public String getProductImageUrl() {
		return productImageUrl;
	}

	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}

	

	public String getProductColor() {
		return productColor;
	}

	public void setProductColor(String productColor) {
		this.productColor = productColor;
	}

	public String getProductMaterial() {
		return productMaterial;
	}

	public void setProductMaterial(String productMaterial) {
		this.productMaterial = productMaterial;
	}

	public List<IncludedCompoment> getIncludedCompoments() {
		return includedCompoments;
	}

	public void setIncludedCompoments(List<IncludedCompoment> includedCompoments) {
		this.includedCompoments = includedCompoments;
	}

	public List<ProductKeyword> getProductKeywords() {
		return productKeywords;
	}

	public void setProductKeywords(List<ProductKeyword> productKeywords) {
		this.productKeywords = productKeywords;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public LocalDateTime getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(LocalDateTime insertTime) {
		this.insertTime = insertTime;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getOrginOfProduct() {
		return orginOfProduct;
	}

	public void setOrginOfProduct(String orginOfProduct) {
		this.orginOfProduct = orginOfProduct;
	}  
	

}

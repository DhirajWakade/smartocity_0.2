INSERT INTO tbl_business_type (business_type_id, business_type_name,is_gst_no_required,business_type_code)
VALUES 
(1,"Wholesale Loose Kirana",1,"WHOLESALELOOSEKIRANA"),
ON DUPLICATE KEY UPDATE    
business_type_name="Wholesale Loose Kirana", is_gst_no_required=1,business_type_code="WHOLESALELOOSEKIRANA";
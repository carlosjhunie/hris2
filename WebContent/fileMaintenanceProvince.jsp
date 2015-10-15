<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Province</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="commonHeader.jsp" %>
<%@include file="commonJtables.jsp" %>
<link rel="stylesheet" type="text/css" href="css/dai.css" />
<link href="js/validationEngine/validationEngine.jquery.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/validationEngine/jquery.validationEngine.js"></script>
<script type="text/javascript" src="js/validationEngine/jquery.validationEngine-en.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">

    $(document).ready(function () {
        $('#containerProvince').jtable({
            title: 'List of Province',
            actions: {
                listAction: '/hris/GetAllProvinceAction',
                createAction:'/hris/AddProvinceAction',
                updateAction: '/hris/UpdateProvinceAction'
                
            },
            fields: {
            	provinceId: {
                    key: true,
                    list: false
                },
                provinceName: {
                    title: 'Province',
                    edit : true 
                }
                
                
            },
            formCreated: function(event, data){
            	data.form.find('input[name="provinceName"]').addClass('validate[required]');
            	data.form.validationEngine();
            },
            //Validate form when it is being submitted
            formSubmitting: function (event, data) {
                return data.form.validationEngine('validate');
            },
            //Dispose validation logic when form is closed
            formClosed: function (event, data) {
                data.form.validationEngine('hide');
                data.form.validationEngine('detach');
            }
        });
        $('#containerProvince').jtable('load');
    });
</script>

</head>
<body>
<div><c:import url="header.jsp" /></div>
<div id="content">
<div class="cb" style="height: 20px;"></div>
	<div id="containerProvince"  class="jTableContainerDai"></div>
</div>	
<div><c:import url="footer.jsp" /></div>
</body>
</html>
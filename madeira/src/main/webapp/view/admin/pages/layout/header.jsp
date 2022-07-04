<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- Importa a biblioteca do jsp -->
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AdminLTE 2 | Dashboard</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  
  <!-- custom CSS -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/view/admin/dist/css/custom.css">
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/view/admin/bootstrap/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/view/admin/dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/view/admin/dist/css/skins/_all-skins.min.css">
  <!-- iCheck -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/view/admin/plugins/iCheck/flat/blue.css">
  <!-- Morris chart -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/view/admin/plugins/morris/morris.css">
  <!-- jvectormap -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/view/admin/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
  <!-- Date Picker -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/view/admin/plugins/datepicker/datepicker3.css">
  <!-- Daterange picker -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/view/admin/plugins/daterangepicker/daterangepicker.css">
  <!-- bootstrap wysihtml5 - text editor -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/view/admin/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if y-u view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

<header class="main-header">
    <!-- Logo -->
    <a href="/madeira/ServletIndex" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>A</b>LT</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>Admin</b>LTE</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>

      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">

          <!-- User Account: style can be found in dropdown.less -->
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <img src="${pageContext.request.contextPath}/view/admin/dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
              <span class="hidden-xs">${ user.nome }</span>
            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header">
                <img src="${pageContext.request.contextPath}/view/admin/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">

                <p>
                  	${ user.nome }
                </p>
              </li>
              
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="#" class="btn btn-default btn-flat">Profile</a>
                </div>
                <div class="pull-right">
                  <a href="/madeira/ServletLogin?acao=logoff" class="btn btn-default btn-flat">Sign out</a>
                </div>
              </li>
            </ul>
          </li>
          <!-- Control Sidebar Toggle Button -->
          <li>
            <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
          </li>
        </ul>
      </div>
    </nav>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="${pageContext.request.contextPath}/view/admin/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>${ user.nome }</p>
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>

      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu">
        <li class="header">MAIN NAVIGATION</li>
        <li class="active treeview">
          <a href="#">
            <i class="fa fa-dashboard"></i> <span>Dashboard</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li class="active"><a href="${pageContext.request.contextPath}/ServletIndex"><i class="fa fa-circle-o"></i> Dashboard v1</a></li>
          </ul>
        </li>
        
        <li class="treeview"> <!-- Encomenda -->
          <a href="#">
            <i class="fa fa-user"></i>
            <span>Cliente</span>
            <span class="pull-right-container">
              <span class="label label-success pull-right">2</span>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="${pageContext.request.contextPath}/view/admin/pages/formCreateCliente.jsp"><i class="fa fa-plus"></i> Novo Cliente</a></li>
            <li><a href="${pageContext.request.contextPath}/ServletCliente"><i class="fa fa-list"></i> Listar Clientes</a></li>
          </ul>
        </li> <!-- Fim Encomenda -->
        
        <li class="treeview"> <!-- Encomenda -->
          <a href="#">
            <i class="fa fa-files-o"></i>
            <span>Encomenda</span>
            <span class="pull-right-container">
              <span class="label label-primary pull-right">2</span>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="${pageContext.request.contextPath}/ServletListarCliente"><i class="fa fa-plus"></i> Nova Encomenda</a></li>
            <li><a href="${pageContext.request.contextPath}/ServletItemEncomenda"><i class="fa fa-list"></i> Listar Encomendas</a></li>
          </ul>
        </li> <!-- Fim Encomenda -->
        
        <li class="treeview"> <!-- Móvel -->
          <a href="#">
            <i class="fa fa-hotel"></i>
            <span>Móvel</span>
            <span class="pull-right-container">
              <span class="label label-danger pull-right">2</span>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="${pageContext.request.contextPath}/ServletListarLinhaMovel"><i class="fa fa-plus"></i> Novo Móvel</a></li>
            <li><a href="${pageContext.request.contextPath}/ServletMovel"><i class="fa fa-list"></i> Listar Móveis</a></li>
          </ul>
        </li> <!-- Fim Móvel -->
        
        <li class="treeview"> <!-- Linha Móvel -->
          <a href="#">
            <i class="fa fa-map-o"></i>
            <span>Linha Móvel</span>
            <span class="pull-right-container">
              <span class="label label-info pull-right">2</span>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="${pageContext.request.contextPath}/view/admin/pages/formCreateLinhaMovel.jsp"><i class="fa fa-plus"></i> Nova Linha Móvel</a></li>
            <li><a href="${pageContext.request.contextPath}/ServletLinhaMovel"><i class="fa fa-list"></i> Listar Linhas Móveis</a></li>
          </ul>
        </li> <!-- Fim Linha Móvel -->
        
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>
  
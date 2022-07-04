  <%@ include file="./pages/layout/header.jsp" %>
  
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Dashboard
        <small>Control panel</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="/madeira/ServletIndex" class="active"><i class="fa fa-dashboard"></i> Home</a></li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <!-- Small boxes (Stat box) -->
      <div class="row">
        <div class="col-lg-3 col-xs-6">
          <!-- small box -->
          <div class="small-box bg-aqua">
            <div class="inner">
              <h3>${ item.getQtdEncomenda() }</h3>

              <p>Encomendas</p>
            </div>
            <div class="icon">
              <i class="ion ion-bag"></i>
            </div>
            <a href="/madeira/ServletItemEncomenda" class="small-box-footer">Mais informações <i class="fa fa-arrow-circle-right"></i></a>
          </div>
      </div>
          
         <div class="col-lg-3 col-xs-6">
         <!-- small box -->
         <div class="small-box bg-yellow">
           <div class="inner">
             <h3>${ item.getQtdCliente() }</h3>

             <p>Clientes</p>
           </div>
           <div class="icon">
             <i class="ion ion-person-add"></i>
           </div>
           <a href="/madeira/ServletCliente" class="small-box-footer">Mais informações <i class="fa fa-arrow-circle-right"></i></a>
         </div>
       </div>
       <!-- ./col -->
    	</div>

      <!-- /.row -->

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

<%@ include file="./pages/layout/footer.jsp" %>

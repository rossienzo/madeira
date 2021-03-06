  <%@ include file="./layout/header.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Dashboard
        <small>Control panel</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="/madeira/ServletIndex"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Atualizar Linha M?vel</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">

      <!-- Main row -->
      <div class="row">
        <!-- Left col -->
        <section class="col-lg-12 connectedSortable">
          <!-- Custom tabs (Charts with tabs)-->
          <div class="col-lg-12">
          	<div class="box box-info">
            <div class="box-header with-border">
              <h3 class="box-title">Editar Linha M?vel</h3>
            </div>
            <!-- /.box-header -->
            <c:if test="${ msg.text != null}">
				<div class="box-header msg ${ msg.type }">
	              <h3 class="box-title">${ msg.text }</h3>
	            </div>
			</c:if>
            
            <!-- form start -->
            <form role="form" method="post" action="/madeira/ServletLinhaMovel">
              <div class="box-body">
           	 	<div class="form-group">
                  <input type="text" class="form-control idinput" value="${ lMovel.id }" id="linhaMovelId" name="linhaMovelId" >
                </div>
                <div class="form-group">
                  <label for="linhaMovelNome">Linha M?vel</label>
                  <input type="text" class="form-control" id="linhaMovelNome" name="linhaMovelNome" value="${ lMovel.getNome() }" placeholder="Digite o nome da linha m?vel">
                </div>
                <div class="form-group">
                  <label for="linhaMovelDescricao">Descri??o</label>
                  <input type="text" class="form-control" id="linhaMovelDescricao" name="linhaMovelDescricao" value="${ lMovel.getDescricao() }" placeholder="Digite descri??o do m?vel">
                </div>
              </div>
              <!-- /.box-body -->

              <div class="box-footer">
                <button type="submit" class="btn btn-primary">Atualizar</button>
              </div>
            </form>
          </div>
          </div>
        </section>
        <!-- right col -->
      </div>
      <!-- /.row (main row) -->

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  
  <%@ include file="./layout/footer.jsp" %>
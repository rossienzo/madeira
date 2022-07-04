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
        <li class="active">Atualizar Cliente</li>
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
          	<div class="box box-success">
            <div class="box-header with-border">
              <h3 class="box-title">Editar Cliente</h3>
            </div>
            <c:if test="${ msg.text != null}">
				<div class="box-header msg ${ msg.type }">
	              <h3 class="box-title">${ msg.text }</h3>
	            </div>
			</c:if>
            <!-- /.box-header -->
            
            <!-- form start -->
            <form role="form" method="post" action="/madeira/ServletCliente">
              <div class="box-body">
              	<div class="form-group">
                  <input type="text" class="form-control idinput" value="${ cliente.getId() }" id="id" name="id">
                </div>
                <div class="form-group">
                  <label for="telefone">Telefone</label>
                 <input type="text" class="form-control" id="telefone" name="telefone" value="${ cliente.getTelefone() }" placeholder="Digite o telefone">
                <div class="form-group">
                  <label for="nome">Nome</label>
                  <input type="text" class="form-control" id="nome" name="nome" value="${ cliente.getNome() }" placeholder="Digite o nome">
                </div>
                <div class="form-group">
                  <label for="senha">Senha</label>
                  <input type="password" class="form-control" id="senha" name="senha" placeholder="Digite a senha">
                </div>
                <div class="form-group">
                  <label for="resenha">Repita a senha</label>
                  <input type="password" class="form-control" id="resenha" name="resenha" placeholder="Digite a senha novamente">
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
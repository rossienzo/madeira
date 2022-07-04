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
        <li class="active">Criar M�vel</li>
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
          	<div class="box box-danger">
            <div class="box-header with-border">
              <h3 class="box-title">Novo M�vel</h3>
            </div>
            <c:if test="${ msg.text != null}">
				<div class="box-header msg ${ msg.type }">
	              <h3 class="box-title">${ msg.text }</h3>
	            </div>
			</c:if>
            <!-- /.box-header -->
            <!-- form start -->
            <form role="form" method="post" action="/madeira/ServletMovel?acao=inserir">
              <div class="box-body">
                <div class="form-group">
                  <label for="movelName">M�vel</label>
                  <input type="text" class="form-control" id="movelName" name="movelName" placeholder="Digite o nome do m�vel">
                </div>
                <div class="form-group">
                  <label for="movelModelo">Modelo</label>
                  <input type="text" class="form-control" id="movelModelo" name="movelModelo" placeholder="Digite o modelo do m�vel">
                </div>
                <div class="form-group">
                  <label for="movelDescricao">Descri��o</label>
                  <input type="text" class="form-control" id="movelDescricao" name="movelDescricao" placeholder="Digite a descri��o do m�vel">
                </div>
                <div class="form-group">
                  <label for="movelPreco">Pre�o</label>
                  <input type="number" step=any class="form-control" id="movelPreco" name="movelPreco" placeholder="Digite o pre�o do m�vel">
                </div>
                <div class="form-group">
                  <label for="movelLinha">Linha do m�vel</label>
                  <select id="movelLinha" class="form-control" name="movelLinha">
                    <c:forEach var="linhaMovel" items="${linhasMoveis}">
						<option value="${ linhaMovel.id }">${ linhaMovel.nome } </option>
					</c:forEach>
                  </select>
                </div>
              </div>
              <!-- /.box-body -->

              <div class="box-footer">
                <button type="submit" class="btn btn-primary">Enviar</button>
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
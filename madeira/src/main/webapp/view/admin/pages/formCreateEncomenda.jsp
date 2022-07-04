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
        <li><a href="/madeira/ServletIndexra/view/admin/index.jsp"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Criar Encomenda</li>
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
          	<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Nova Encomenda</h3>
            </div>
            <c:if test="${ msg.text != null}">
				<div class="box-header msg ${ msg.type }">
	              <h3 class="box-title">${ msg.text }</h3>
	            </div>
			</c:if>
            <!-- /.box-header -->
            <!-- form start -->
            <form role="form" method="post" action="/madeira/ServletItemEncomenda?acao=inserir">
              <div class="box-body">
                <div class="form-group">
                  <label for="data">Data</label>
                  <input type="datetime-local" class="form-control" id="data" name="data">
                </div>
                <div class="form-group">
                  <label for="dataPrevista">Data prevista</label>
                  <input type="datetime-local" class="form-control" id="dataPrevista" name="dataPrevista">
                </div>
                <div class="form-group">
                  <label for="dataEntrega">Data de entrega</label>
                  <input type="datetime-local" class="form-control" id="dataEntrega" name="dataEntrega">
                </div>
                <div class="form-group">
                  <label for="dataVencimento">Data de vencimento</label>
                  <input type="datetime-local" step=any class="form-control" id="dataVencimento" name="dataVencimento">
                </div>
                <div class="form-group">
                  <label for="dataPagamento">Data de pagamento</label>
                  <input type="datetime-local" step=any class="form-control" id="dataPagamento" name="dataPagamento">
                </div>
                <div class="form-group">
                  <label for="dataProntidao">Data de prontidão</label>
                  <input type="datetime-local" step=any class="form-control" id="dataProntidao" name="dataProntidao" >
                </div>
                <div class="form-group">
                  <label for="cliente">Cliente</label>
                  <select id="cliente" class="form-control" name="cliente">
                    <c:forEach var="cliente" items="${clientes}">
						<option value="${ cliente.getId() }">${ cliente.getNome() } </option>
					</c:forEach>
                  </select>
                </div>
                <div class="form-group">
                  <label for="qtdEncomendada">Quantidade encomendada</label>
                  <input type="number" class="form-control" id="qtdEncomendada" name="qtdEncomendada">
                </div>
                <div class="form-group">
                  <label for="idMovel">Móvel</label>
                  <select id="idMovel" class="form-control" name="idMovel">
                    <c:forEach var="movel" items="${moveis}">
						<option value="${ movel.getId() }">${ movel.getNome() } </option>
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
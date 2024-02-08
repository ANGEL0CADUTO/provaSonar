package adapter;

import java.math.BigDecimal;

public class CambioValutaRupieAdapter implements ValutaAdapter {
private BigDecimal tassoCambio;

public CambioValutaRupieAdapter(BigDecimal tassoCambio){ this.tassoCambio=tassoCambio;}//COSTRUTTORE PER INSTANZIARE IL TASSO

    @Override
 public BigDecimal convertiCredito(BigDecimal credito){
     return credito.multiply(tassoCambio);
    }

}

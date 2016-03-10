package com.sammyun.codeGenerator;



public class DefaultCodeGenerator implements CodeGenerator
{
    private PrefixGenerator prefixGenerator;
    private SequenceGenerator sequenceGenerator ;
    private SequenceFormater sequenceFormater;

    public synchronized String create() throws Exception
    {
        final String prefix = prefixGenerator == null ? "" : prefixGenerator.create();
        long sequence = sequenceGenerator.next();
        final String strSequence = sequenceFormater == null ? new Long(sequence).toString() : sequenceFormater
                .format(sequence);
        return prefix + strSequence;
    }
    public void setPrefixGenerator(PrefixGenerator prefixGenerator) {
        this.prefixGenerator = prefixGenerator;
    }

    public void setSequenceGenerator(SequenceGenerator sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    public void setSequenceFormater(SequenceFormater sequenceFormater) {
        this.sequenceFormater = sequenceFormater;
    }
    
    
}

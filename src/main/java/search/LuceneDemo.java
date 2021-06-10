package search;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class LuceneDemo {

    public static void main(String[] args) {
        testLucene();
    }

    private static void testLucene() {
        // 构建分词器
        //Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);
        @SuppressWarnings("resource")
		Analyzer analyzer = new IKAnalyzer(true);

        // 获取Lucene的TokenStream对象
        TokenStream tokenStream = null;
        {
            try {
                tokenStream = analyzer.tokenStream("myfield", new StringReader( "这是一个分词的例子，我们来使用一下试试。 Let's use it."));

                // 获取词元位置属性
                OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
                // 获取词元文本属性
                CharTermAttribute termAttribute = tokenStream.addAttribute(CharTermAttribute.class);
                // 获取词元文本属性
                TypeAttribute typeAttribute = tokenStream.addAttribute(TypeAttribute.class);

                //重置TokenStream（重置StringReader） 
                tokenStream.reset();
                // 迭代获取分词结果
                while (tokenStream.incrementToken()) {
                    System.out.println(offsetAttribute.startOffset() + "-" + offsetAttribute.endOffset()
                            + termAttribute.toString() + "|" + typeAttribute.type());
                }
                tokenStream.end();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // 释放TokenStream的所有资源
                if (tokenStream != null) {
                    try {
                        tokenStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}

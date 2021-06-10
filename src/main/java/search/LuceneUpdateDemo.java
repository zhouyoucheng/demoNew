package search;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

public class LuceneUpdateDemo {
	 public static void main(String[] args) {
	        // 实例化IKAnalyzer分词器
	        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);

	        Directory directory = null;
	        IndexWriter iwriter;
	        try {
	            // 索引目录
	            directory = new SimpleFSDirectory(new File("D://test/lucene_index"));
	            // 配置IndexWriterConfig
	            IndexWriterConfig iwConfig = new IndexWriterConfig(Version.LUCENE_47, analyzer);
	            iwConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
	            iwriter = new IndexWriter(directory, iwConfig);
	            // 写入索引
	            Document doc = new Document();
	            String id = "1510579934220";
	            doc.add(new StringField("ID", id, Field.Store.YES));
	            doc.add(new TextField("blog", "更新文档后->达摩院一定也必须要超越英特尔，必须超越微软，必须超越IBM，因为我们生于二十一世纪，我们是有机会后发优势的。", Field.Store.YES));
	            //先根据Term ID 删除，在建立新的索引
	            iwriter.updateDocument(new Term("ID", id), doc);
	            iwriter.close();
	            System.out.println("更新索引成功:" + 1511233039462L);
	        } catch (CorruptIndexException e) {
	            e.printStackTrace();
	        } catch (LockObtainFailedException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (directory != null) {
	                try {
	                    directory.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
}

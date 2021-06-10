package search;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

public class LuceneDeleteDemo {
	 @SuppressWarnings("unused")
	public static void main(String[] args) {
	        // Lucene Document的域名
	        @SuppressWarnings("unused")
			String fieldName = "blog";
	        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);
	        Directory directory = null;
	        IndexReader ireader = null;
	        IndexSearcher isearcher;
	        IndexWriter iwriter = null;
	        try {
	            //索引目录
	            directory = new SimpleFSDirectory(new File("D://test/lucene_index"));
	            // 配置IndexWriterConfig
	            IndexWriterConfig iwConfig = new IndexWriterConfig(Version.LUCENE_47, analyzer);
	            iwConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
	            ireader = DirectoryReader.open(directory);
	            iwriter = new IndexWriter(directory, iwConfig);
	            iwriter.deleteDocuments(new Term("ID","1510579934220"));
	            //使用IndexWriter进行Document删除操作时，文档并不会立即被删除，而是把这个删除动作缓存起来，当IndexWriter.Commit()或IndexWriter.Close()时，删除操作才会被真正执行。
	            iwriter.commit();
	            iwriter.close();
	            ireader.close();
	        } catch (Exception e) {
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
